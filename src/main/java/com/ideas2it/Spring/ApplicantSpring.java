package com.ideas2it.Spring;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ideas2it.model.ApplicantDTO;
import com.ideas2it.model.RecruiterDTO;
import com.ideas2it.service.ApplicantService;
import com.ideas2it.service.impl.ApplicantServiceImpl;
import com.ideas2it.util.DateUtil;
import com.ideas2it.exception.HireWorldException;
import com.ideas2it.logger.HireWorldLogger;

/**
 * <p>
 * Gets input for the applicants and then displays the results of applicant's
 * operations.
 * </p>
 *
 */

@Controller
public class ApplicantSpring {

	private ApplicantService applicantService = new ApplicantServiceImpl();

	@RequestMapping("/createApplicant")
	public String insertApplicant(Model model) {
		model.addAttribute("applicantDTO", new ApplicantDTO());
		return "createApplicant";
	}

	@PostMapping({ "/insertApplicant" })
	public String createApplicant(@ModelAttribute ("applicantDTO") ApplicantDTO applicantDTO, @RequestParam String birthDate,
			Model model, HttpServletRequest request) {

		try {
			String message = null;
			HttpSession session = request.getSession();
			applicantDTO.setDateOfBirth(DateUtil.getParsedDateOfBirth(request.getParameter("birthDate")));

			applicantDTO.setRecruiters((List<RecruiterDTO>) session.getAttribute("recruitersDTO"));
			if (0 == applicantDTO.getId()) {
				model.addAttribute("applicantDTO", applicantService.createApplicant(applicantDTO));
				message = "Insert Successfully";			
			} else {
				model.addAttribute("applicantDTO", applicantService.updateApplicantById(applicantDTO));
				message = "Update Successfully";
			}
			model.addAttribute("message", message);
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "createApplicant";
	}

	/**
	 * <p>
	 * To display all the applicants stored in the applicants table. if the
	 * applicants table is empty, display no record found.
	 * </p>
	 */
	@GetMapping("/displayApplicants")
	private String displayApplicants(Model model) {

		try {
			List<ApplicantDTO> applicantsDTO = applicantService.displayApplicants();
			model.addAttribute("applicantsDTO", applicantsDTO);

			if (!applicantsDTO.isEmpty()) {
				model.addAttribute("message", applicantsDTO);
			} else {
				model.addAttribute("message", "No Record Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}

		return "displayApplicants";
	}

	@PostMapping({ "/getApplicantById" })
	private String getApplicantById(@RequestParam("applicantId") int applicantId, Model model,
			HttpServletRequest request) {
		try {
			ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
			
			model.addAttribute("applicantDTO", applicantDTO);	
			if (null != applicantDTO) {
				model.addAttribute("applicantDTO", applicantDTO);	
			} else {
				model.addAttribute("Message", "No Record Found");
			}			//request.getSession().setAttribute("recruitersDTO", applicantDTO.getRecruiters());
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "getApplicantById";
	}

	@PostMapping("/removeApplicant")
	private String removeApplicantById(@RequestParam("applicantId") int applicantId, Model model) {
		System.out.println("Hiiii");
		try {
			if (applicantService.isIdExist(applicantId)) {
				System.out.println(applicantService.removeApplicantById(applicantId));
				if (applicantService.removeApplicantById(applicantId)) {
					model.addAttribute("message", "Deleted Successfully");
				} else {
					model.addAttribute("message", "Deletion Unsuccessfull");
				}
			} else {
				model.addAttribute("message", "Applicant Not Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "removeApplicant";
	}

	/**
	 * <p>
	 * To assign the recruiters for applicant.
	 * </p>
	 */
	/*
	 * @PostMapping("/assignRecruiters") private String
	 * assignRecruiters(@RequestParam int id, Model model, HttpServletRequest
	 * request) { String view = null; try { HttpSession session =
	 * request.getSession(); boolean isPresent = applicantService.isIdExist(id);
	 * session.setAttribute("applicantId", id); if (isPresent) {
	 * model.addAttribute("isPresent", isPresent); view = "assignRecruiters"; } else
	 * { model.addAttribute("message", "Applicant not found"); view = "error"; } }
	 * catch (HireWorldException hireWorldException) {
	 * HireWorldLogger.displayError(hireWorldException.getMessage()); } return view;
	 * }
	 */

	/**
	 * <p>
	 * To get the applicants by Recruiter id and display the applicants.
	 * </p>
	 */
	@GetMapping("/getApplicantsByRecruiterId")
	private String getApplicantsByRecruiterId(@RequestParam("recruiterId") int recruiterId, Model model,
			HttpServletRequest request) {
		String view = null;
		try {
			List<ApplicantDTO> applicantsDTO = applicantService.getApplicantsByRecruiterId(recruiterId);
			if (!applicantsDTO.isEmpty()) {
				model.addAttribute("applicantsDTO", applicantsDTO);
				view = "displayApplicants";
			} else {
				model.addAttribute("error", "Applicants Not found");
				view = "error";
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return view;
	}

	@PostMapping({ "/updateApplicant" })
	private String updateApplicantById(@RequestParam("applicantId") int applicantId, Model model,
			HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
			session.setAttribute("recruitersDTO", applicantDTO.getRecruiters());
			if (applicantService.isIdExist(applicantId)) {
				model.addAttribute("applicantDTO", applicantDTO);
			} else {
				model.addAttribute("message", "Applicant Not Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "createApplicant";
	}
			//ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
			
			
			

//			Date dateOfBirth = DateUtil.getParsedDateOfBirth(request.getParameter("dateOfBirth"));
//
//			if (applicantService.isIdExist(applicantId)) {
//				if (applicantService.updateApplicantById(applicantDTO) != null) {
//					model.addAttribute("message", "Updation Successfully");
//				} else {
//					model.addAttribute("message", "Updation Unsuccessfull");
//				}
//			} else {
//				model.addAttribute("message", "Applicant Not Found");
//			}
//			model.addAttribute("message", message);
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//		return "updateApplicant";
//	}
}
