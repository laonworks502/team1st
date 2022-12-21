package com.laonworks502.team1st.controller.users;

import com.laonworks502.team1st.SHA256Util;
import com.laonworks502.team1st.model.board.Pagination;
import com.laonworks502.team1st.model.post.PostBean;
import com.laonworks502.team1st.model.scrap.ScrapListBean;
import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.service.users.CompanyUserServiceImpl;
import com.laonworks502.team1st.service.users.GeneralUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class GeneralUserController {

    @Autowired
    private GeneralUserServiceImpl gus;

    @Autowired
    private CompanyUserServiceImpl cus;

/*
    // 일반 회원 마이페이지
    @RequestMapping(value = "/generalmypage")
    public String generalmypage(HttpSession session,
                                Model model,
                                @ModelAttribute ScrapListBean myminiscrap100,
                                @ModelAttribute ScrapListBean myminiscrap200,
                                @ModelAttribute ScrapListBean myminiscrap300

//                                @ModelAttribute GeneralUserBean gub
                                )throws Exception{

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        GeneralUserBean gub = gus.checkGeneraluser(email);

        log.info("generalmypage:" + gub.getEmail());


        model.addAttribute("gub", gub);

        model.addAttribute("myminiscrap100",myminiscrap100);
        model.addAttribute("myminiscrap200",myminiscrap200);
        model.addAttribute("myminiscrap300",myminiscrap300);

        return "generaluser/generalmypage";
    }
*/

    // 회원가입 폼
    @RequestMapping(value = "/general-user-insert")
    public String generaluserinsert(GeneralUserBean gub) throws  Exception{

        log.info("회원가입 페이지");
        return "generaluser/generaluserinsert";
    }

    // 회원가입 실행
    @RequestMapping("/general-user-insert-ok")
    public String generaluserinsert_ok(GeneralUserBean gub,
                                       @RequestParam("passwd") String passwd,
                                       Model model) throws Exception {

        String salt = SHA256Util.generateSalt();                        // 8 바이트의 랜덤 수열 발생. salt 는 암호화 키
        String savepasswd = SHA256Util.getEncrypt_gu(passwd, salt);   // 입력한 passwd를 암호화 키 salt를 이용해서 SHA256방식으로 암호화
        gub.setPasswd(savepasswd);
        gub.setSalt(salt);  // salt 컬럼에 설정

        gus.addGeneralUser(gub);

        log.info("비밀번호 암호화 저장 : " + gub.getPasswd());
        log.info("회원가입 완료");

        return "generaluser/loginForm"; // 가입 후 일반회원 로그인페이지로 이동
    }

    // 이메일 중복 체크
    @PostMapping("/useremailcheck")
    @ResponseBody
    public int generalemailcheck(@RequestParam("email") String email) throws Exception{

        int cnt = gus.emailDuplicatecheck(email);

        // generalinsert 뷰 파일에서 이메일체크시 checkgeneral.js 에 있는
        // 이메일 입력하는 input태그에 oninput시 checkemailch() 함수 실행하도록 설정.
        // checkemailch()에 cnt값을 return해서 이메일 중복 판단

        return cnt;
    }

    // 회원수정 폼
    @RequestMapping("/general-user-edit")
    public String generaluseredit(HttpSession session,
                                  Model model,
                                  GeneralUserBean gub) throws Exception {

        // LoginBean에는 2개의 컬럼 존재 : email, authority(기업/일반 구분하는 역할)이 존재.
        // 세션을 불러들임
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String email = loginBean.getEmail();    // 세션값의 email불러오기

		gub = gus.checkGeneraluser(email);      // 세션으로 불러들인 email을 변수삼아, 해당 email의 정보 불러오는 함수
        model.addAttribute("gub",gub);  // model(name, value) 로 뷰에 넘겨줌.

        log.info("회원수정 페이지");

        return "generaluser/generaluseredit";
    }

    // 회원수정 실행
    @RequestMapping(value="/general-user-edit-ok", method = RequestMethod.POST)    // 수정시 method = RequestMethod.POST필수
    public String generaluseredit_ok(HttpSession session,
                                     HttpServletRequest request,
                                     @RequestParam("passconfirm") String passconfirm,
                                     @RequestParam("email") String email,
                                     @ModelAttribute GeneralUserBean gub,
                                     Model model) throws Exception {

        GeneralUserBean old = gus.checkGeneraluser(gub.getEmail());

        log.info(gub.getEmail());
        log.info("general_user테이블의 passwd컬럼 : " + old.getPasswd());

        String salt = old.getSalt();
        log.info("salt값 : " + salt);
        log.info("수정폼 입력값 암호화 : " + SHA256Util.getEncrypt_gu(passconfirm, salt));

        int result = 0;
        if(old.getPasswd().equals(SHA256Util.getEncrypt_gu(passconfirm, salt))){

            result = 3;
            gus.updateGeneraluser(gub);
            log.info("[" +gub.getEmail() + "] 의 정보 수정 성공");
//            return "redirect:/scrap/generalmypage";
        }else{
            result = 2;
            log.info("비밀번호 틀림");
        }
        model.addAttribute("result", result);

        return "generaluser/loginResult";

    }

    // 회원삭제 폼
    @RequestMapping("/general-user-delete")
    public String generaluserdelete(HttpSession session,
                                    GeneralUserBean gub,
                                    Model model) throws Exception {

		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

        gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원삭제 페이지");

        return "generaluser/generaluserdelete";
    }

    // 회원삭제 실행
    @RequestMapping("general-user-delete-ok")
    public String generaluserdelete_ok(@RequestParam("exit_reason") String exit_reason,
                                       @RequestParam("passconfirm") String passconfirm,
                                       HttpSession session,
                                       GeneralUserBean gub,
                                       Model model) throws Exception {

		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

        gub.setExit_reason(exit_reason);

        GeneralUserBean old = gus.checkGeneraluser(gub.getEmail());

        String salt = old.getSalt();    // 바로 gub.getSalt 호출하면 null 값이 나와서 String으로 변환.

        log.info(salt);
        log.info("탈퇴폼에서 입력한 passconfirm값 암호화 : " + SHA256Util.getEncrypt_gu(passconfirm, salt));

        int result1 = 0;
        if(old.getPasswd().equals(SHA256Util.getEncrypt_gu(passconfirm, salt))){

            gus.deleteGeneraluser(gub);
            log.info("[" + gub.getEmail() +"] 의 탈퇴사유 : " + gub.getExit_reason());

            session.invalidate();

            result1 = 4;

        }else{
            result1 = 2;
            log.info("비밀번호 틀림");
        }

        model.addAttribute("result", result1);

        return "generaluser/loginResult";
    }

    @RequestMapping("general-boardlist")
    public String generalmyboardlist(@RequestParam(value = "page", required = false, defaultValue = "1")
                                     Integer page, HttpServletRequest request, GeneralUserBean gub,
                                     HttpSession session, Model model, String writer) throws Exception{

        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        gub = gus.checkGeneraluser(email);

        log.info(gub.getEmail() + " 의 작성한 게시글 목록 in");

        // 기업회원이 작성한 모든 총 게시물 수 구하기
        writer = gub.getEmail();
        int postTotal = gus.countAllGeneralPosts(writer);
        log.info("회원 총 게시물 수:" + postTotal);

        Pagination pg = new Pagination(100, postTotal, 10);
        model.addAttribute("pg", pg);

        // 리스트 담기
        List<PostBean> postList = gus.getGeneralBoardList(writer, pg.getStartPostNo(), pg.getPAGES_COUNT());

        model.addAttribute("posts", postList);
        model.addAttribute("gub", gub);

        return "generaluser/general-boardlist";
    }

    // 이력서 업로드
    @RequestMapping(value="resume-upload", method = RequestMethod.POST)
    public String resumeupload(@RequestParam("file") MultipartFile file,
                         HttpSession session,
                         HttpServletRequest request,
                         Model model,
                         GeneralUserBean gub) throws Exception{

        // 파일 업로드 경로는 배포시 서버 측으로 설정해야한다.
//		String email = (String)session.getAttribute("email"); => 대신 밑에 두 줄로 대체
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈

        log.info("파일명 : "  + fileRealName);
        log.info("용량크기 : " + size + " byte");

        // 서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        log.info("확장자명 : " + fileExtension);
        String uploadFolder = "C:\\test\\upload";

		/* UUID 설명 :
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜덤 문자(UUID함수)를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
        // 파일명 랜덤설정 함수인 UUID 설정 부분. 현재 컨트롤러에서 사용하지는 않을 것임.
        UUID uuid = UUID.randomUUID();
        log.info("UUID실행시" + uuid.toString());
        String[] uuids = uuid.toString().split("-");

        String uniqueName = uuids[0];
        log.info("UUID로 파일명 저장시 : " + uniqueName + fileExtension);

        // path + uniqueName
        String resume = uniqueName+fileExtension;
        // uploadFolder + "\\" +
        log.info("resume = "+resume);

        File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // uuid 적용할시
        File file1 = new File(uploadFolder + "\\" + fileRealName);
        try {
            file.transferTo(file1); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
//            file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
            gub.setResume(fileRealName);
            gub.setEmail(email);
            int result = gus.resumeupload(gub);
            if(result == 1) log.info("파일 업로드 -> " + gub.getResume());
            model.addAttribute("gub", gub);      // -> 모델 설정해야 곧바로 웹에 표시가능

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/scrap/generalmypage";
    }

    // 이력서 다운로드
    @RequestMapping(value = "/download")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpSession session,
                         GeneralUserBean gub,
                         @RequestParam("resume") String resume,
                         Model model) throws UnsupportedEncodingException {

        String uploadFolder = "C:\\test\\upload";

        // request.getRealPath("upload") => 웹페이지생성 디렉토리인 webapp아래에 괄호 안 ("upload")폴더를 생성하는 함수
        // => C:\Users\sky66\IdeaProjects\team1st\src\main\webapp 안에 upload 생성.
        // ex) String uploadFolder = request.getRealPath("upload");

        log.info("download...");

        String path = uploadFolder + "\\" + resume;
        log.info("파일을 가져올 path = " + path);

        File file = new File(path);     // path = uploadFolder+"\\"+uniqueName + fileExtension;
        String downName = file.getName(); //다운로드 받을 파일명을 절대경로로  구해옴

        log.info("다운받은 파일 : " + downName);

        // 이 부분이 한글 파일명이 깨지는 것을 방지해 줍니다
        downName = new String(downName.getBytes("utf-8"), "iso-8859-1");

        // octet-stream은 8비트로 된 일련의 데이터를 뜻합니다. 지정되지 않은 파일 형식을 의미합니다.
        // response.setHeader("Content-Type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\"");

        FileInputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = response.getOutputStream();
            FileCopyUtils.copy(in, out);
            model.addAttribute(resume);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
