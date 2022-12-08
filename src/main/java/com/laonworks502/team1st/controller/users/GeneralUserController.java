package com.laonworks502.team1st.controller.users;

import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.model.users.LoginBean;
import com.laonworks502.team1st.model.users.UserBean;
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
import java.util.UUID;

@Slf4j
@Controller
public class GeneralUserController {

    @Autowired
    private GeneralUserServiceImpl gus;


    // 회원가입 폼
    @RequestMapping(value = "/generaluserinsert")
    public String generaluserinsert(GeneralUserBean gub) throws  Exception{

        log.info("회원가입 페이지");
        return "generaluser/generaluserinsert";
    }

    // 회원가입 실행
    @RequestMapping("generaluserinsert_ok")
    public String generaluserinsert_ok(GeneralUserBean gub,
                                       Model model) throws Exception {

        gus.joinUser(gub);

        log.info("회원가입 완료");    // 뷰에 에러뜸 회원가입 값은 넘어감

        return "generaluser/loginForm"; // 가입 후 로그인페이지로 이동
    }

    // 이메일중복체크
    @PostMapping("/useremailcheck")
    @ResponseBody
    public int generalemailcheck(@RequestParam("email") String email) throws Exception{

        int cnt = gus.emailDuplicatecheck(email);

        return cnt;
    }

    // 회원수정 폼
    @RequestMapping("generaluseredit")
    public String generaluseredit(HttpSession session, Model model) throws Exception {

		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();

        GeneralUserBean gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원수정 페이지");

        return "generaluser/generaluseredit";
    }

    // 회원수정 실행
    @RequestMapping(value="generaluseredit_ok", method = RequestMethod.POST)    // 수정시 method = RequestMethod.POST필수
    public String generaluseredit_ok(HttpSession session,
                                     HttpServletRequest request,
                                     GeneralUserBean gub) throws Exception {

        GeneralUserBean old = gus.checkGeneraluser(gub.getEmail());

        int result = gus.updateGeneraluser(gub);
        if(result == 1) log.info("수정 성공");

        return "generaluser/loginsuccess";
    }

    // 회원삭제 폼
    @RequestMapping("generaluserdelete")
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
    @RequestMapping("generaluserdelete_ok")
    public String generaluserdelete_ok(@RequestParam("exit_reason") String exit_reason,
                                       HttpSession session,

                                       GeneralUserBean gub) throws Exception {

		LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
		String email = loginBean.getEmail();
        
		gub.setExit_reason(exit_reason);
        int result = gus.deleteGeneraluser(gub);
        if(result == 1) log.info("탈퇴사유 : " + gub.getExit_reason());

        session.invalidate();

        return "generaluser/loginForm";
    }

    // 이력서 업로드
    @RequestMapping(value="resumeupload", method = RequestMethod.POST)
    public String resumeupload(@RequestParam("file") MultipartFile file,
                         HttpSession session,
                         HttpServletRequest request,
                         Model model,
                         GeneralUserBean gub) throws Exception{

//		String email = (String)session.getAttribute("email");
        LoginBean loginBean = (LoginBean) session.getAttribute("loginBean");
        String email = loginBean.getEmail();

        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈

        System.out.println("파일명 : "  + fileRealName);
        System.out.println("용량크기(byte) : " + size);

        //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:\\test\\upload";

		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜덤 문자(UUID함수)를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        String[] uuids = uuid.toString().split("-");

        String uniqueName = uuids[0];
        System.out.println("생성된 고유문자열:" + uniqueName);
        System.out.println("확장자명:" + fileExtension);

        // path + uniqueName
        String resume = uniqueName+fileExtension;
        // uploadFolder + "\\" +
        System.out.println("resume파일명 = "+resume);

        // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

        File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
        try {
            file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
            gub.setResume(resume);
            gub.setEmail(email);
            int result = gus.resumeupload(gub);
            if(result == 1) log.info("파일 업로드 -> " + gub.getResume());
            model.addAttribute("gub", gub);      // -> 모델 설정해야 곧바로 웹에 표시가능

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "generaluser/generalmypage";
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

        System.out.println("download...");

        String path = uploadFolder + "\\" + resume; // ${fileName}= ${resume}; fname = fname;
        System.out.println("path=" + path);

        File file = new File(path);     // path = uploadFolder+"\\"+uniqueName + fileExtension;
        String downName = file.getName(); //다운로드 받을 파일명을 절대경로로  구해옴

        System.out.println(downName);

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
