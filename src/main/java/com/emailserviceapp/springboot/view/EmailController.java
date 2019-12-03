package com.emailserviceapp.springboot.view;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.emailserviceapp.springboot.service.EmailJobTrigger;

@Controller
public class EmailController {

	@GetMapping({ "/" })
	public String landing() {
		
		return "landing";
	}

	@GetMapping({ "/upload" })
	public String upload() {

		return "uploadpage";
	}

	/*
	 * @PostMapping({ "/uploadcsv" }) public String uploadcsv(Model model,
	 * 
	 * @RequestParam(value = "name", required = false, defaultValue = "World")
	 * String name) { model.addAttribute("name", name);
	 * 
	 * System.out.println("Inside uploadcsv controller....!!!"); return "uploadcsv";
	 * }
	 * 
	 * //@PostMapping({"/uploadcsvaction"})
	 * 
	 * @RequestMapping(value = "/uploadcsvaction", method = RequestMethod.POST)
	 * public String uploadcsvaction(HttpServletRequest request, Model model) {
	 * 
	 * System.out.println("Inside uploadcsvaction controller....!!!");
	 * System.out.println("Http servlet request " + request);
	 * 
	 * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
	 * request; System.out.println("Multipart Http servlet request " +
	 * multipartRequest); // List<MultipartFile> multipartFileList =
	 * multipartRequest.getFiles("csv"); MultipartFile multipartFile =
	 * multipartRequest.getFile("file"); System.out.println("Multi part file " +
	 * multipartFile);
	 * 
	 * System.out.println("Original file name " +
	 * multipartFile.getOriginalFilename());
	 * 
	 * return "/trigger"; }
	 * 
	 */
	@PostMapping({ "/trigger" })
	public String trigger(HttpServletRequest request, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {

		System.out.println("User name is " + userName);
		//System.out.println("Password is " + password);

		System.out.println("Http servlet request " + request);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		System.out.println("Multipart Http servlet request " + multipartRequest);
		
		MultipartFile multipartFile = multipartRequest.getFile("file");
		System.out.println("Multi part file " + multipartFile);

		System.out.println("Original file name " + multipartFile.getOriginalFilename());

			try {
				
				File csvFile = ResourceUtils.getFile("classpath:demo_copy.csv");
				multipartFile.transferTo(csvFile);

				System.out.println("Transferrred file object....!!!");
				EmailJobTrigger emailJobTrigger = new EmailJobTrigger();
				emailJobTrigger.triggerEmailService(userName, password, csvFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return "result";
	}

	@GetMapping({ "/result" })
	public String result() {
		
		return "resultindex";
	}

	@GetMapping({ "/resultview" })
	public String resultview(@RequestParam(value = "page", required = false) String page) {
		// model.addAttribute("name", name);

		return "resultview";
	}
}
