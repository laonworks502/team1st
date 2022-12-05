package com.laonworks502.team1st.controller.users;

import com.laonworks502.team1st.model.users.GeneralUserBean;
import com.laonworks502.team1st.service.users.GeneralUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class GeneralUserController {

    @Autowired
    private GeneralUserServiceImpl gus;

    // 로그인 폼
    @RequestMapping("generalloginForm")
    public String generaluserloginForm() throws Exception{

        return "generaluser/loginForm";
    }

    // 로그인 실행
    @RequestMapping("loginsuccess")
    public String generaluserlogin_ok(GeneralUserBean gub,
                                      HttpSession session,
                                      Model model,
                                      @RequestParam("email") String email,
                                      @RequestParam("passwd") String passwd) throws Exception{

        int result = 0;

        gub = gus.checkGeneraluser(email);

        if(gub == null){    // 등록되지 않은 회원

            result = 1;
            model.addAttribute("result", result);

            return "generaluser/loginResult";

        }else{              // 등록 회원 확인됨
            if(gub.getPasswd().equals(passwd)) {        // 비번 같아서 로그인됨
                session.setAttribute("email", email);
                model.addAttribute("gub",gub);
                log.info("로그인성공");

                return "generaluser/loginsuccess";
            }else{                                      // 비번 달라서 로그인 안됨
                result = 2;
                model.addAttribute("result", result);

                return "generaluser/loginResult";
            }
        }
    }

    // 로그아웃
    @RequestMapping("loginselect")
    public String userlogout(HttpSession session) {
        session.invalidate();

        log.info("로그아웃");
        return "generaluser/loginselect";
    }

    // 회원가입 폼
    @RequestMapping(value = "/generaluserinsert")
    public String generaluserinsert(GeneralUserBean gub) throws  Exception{

        return "generaluser/generaluserinsert";
    }

    // 회원가입 실행
    @RequestMapping("generaluserinsert_ok")
    public String generaluserinsert_ok(GeneralUserBean gub,
                                       Model model) throws Exception {

        gus.joinUser(gub);

        log.info("companyuserinsert_ok!");    // 뷰에 에러뜸 회원가입 값은 넘어감

        return "generaluser/loginForm"; // 가입 후 로그인페이지로 이동
    }

    // 이메일중복체크
    @PostMapping("/useremailcheck")
    @ResponseBody
    public int emailcheck(@RequestParam("email") String email) throws Exception{

        int cnt = gus.emailDuplicatecheck(email);

        return cnt;
    }

    // 회원수정 폼
    @RequestMapping("generaluseredit")
    public String generaluseredit(HttpSession session, Model model) throws Exception {

        String email = (String)session.getAttribute("email");

        GeneralUserBean gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원수정폼에서 회원정보 불러오기");

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

        String email = (String)session.getAttribute("email");

        gub = gus.checkGeneraluser(email);

        model.addAttribute("gub",gub);

        log.info("회원삭제폼");

        return "generaluser/generaluserdelete";
    }

    // 회원삭제 실행
    @RequestMapping("generaluserdelete_ok")
    public String generaluserdelete_ok(@RequestParam("exit_reason") String exit_reason,
                                       HttpSession session,

                                       GeneralUserBean gub) throws Exception {

        String email = (String)session.getAttribute("email");
        gub.setExit_reason(exit_reason);
        int result = gus.deleteGeneraluser(gub);
        if(result == 1) log.info("탈퇴사유 : " + gub.getExit_reason());

        session.invalidate();

        return "generaluser/loginForm";
    }

    /*[비밀번호 찾기 폼]*/
    @RequestMapping("userpwfind")
    public String userfindPasswdUser() {
        log.info("컨트롤러 들어옴(findPasswdUser)");

        return "companyuser/pwfind";
    }


    @RequestMapping(value="/resumeupload", method = RequestMethod.POST)
    public String resumeupload(@RequestParam("file") MultipartFile file,
                         HttpSession session,
                         HttpServletRequest request,
                         Model model,
                         GeneralUserBean gub) throws Exception{

        String email = (String)session.getAttribute("email");


        String fileRealName = file.getOriginalFilename(); //파일명을 얻어낼 수 있는 메서드!
        long size = file.getSize(); //파일 사이즈

        System.out.println("파일명 : "  + fileRealName);
        System.out.println("용량크기(byte) : " + size);
        //서버에 저장할 파일이름 fileextension으로 .jsp이런식의  확장자 명을 구함
        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."),fileRealName.length());
        String uploadFolder = "C:\\test\\upload";
        // File file부터 그대로 복사


		/*
		  파일 업로드시 파일명이 동일한 파일이 이미 존재할 수도 있고 사용자가
		  업로드 하는 파일명이 언어 이외의 언어로 되어있을 수 있습니다.
		  타인어를 지원하지 않는 환경에서는 정산 동작이 되지 않습니다.(리눅스가 대표적인 예시)
		  고유한 랜던 문자를 통해 db와 서버에 저장할 파일명을 새롭게 만들어 준다.
		 */

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        String[] uuids = uuid.toString().split("-");

        String uniqueName = uuids[0];
        System.out.println("생성된 고유문자열:" + uniqueName);
        System.out.println("확장자명:" + fileExtension);

        // path + uniqueName
        String resume = uploadFolder+"\\"+uniqueName+fileExtension;

        System.out.println("resume로컬주소 = "+resume);

        // File saveFile = new File(uploadFolder+"\\"+fileRealName); uuid 적용 전

        File saveFile = new File(uploadFolder+"\\"+uniqueName + fileExtension);  // 적용 후
        try {
            file.transferTo(saveFile); // 실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다.)
            gub.setResume(resume);
            int result = gus.resumeupload(gub);
            if(result == 1) log.info("파일 업로드 -> " + gub.getResume());


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "generaluser/loginsuccess";
    }

//    @GetMapping("/download")
//    public ResponseEntity<Object> download("download") {
//        String path = "C:/uploadFile/jarzip.PNG";
//
//        try {
//            Path filePath = Paths.get(path);
//            Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기
//
//            File file = new File(path);
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getName()).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
//
//            return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
//        } catch(Exception e) {
//            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
//        }
//    }

}
