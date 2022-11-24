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
import com.ideas2it.service.RecruiterService;
import com.ideas2it.service.impl.ApplicantServiceImpl;
import com.ideas2it.service.impl.RecruiterServiceImpl;
import com.ideas2it.exception.HireWorldException;
import com.ideas2it.logger.HireWorldLogger;

/**
 * <p>
 * Gets input for the Recruiters and then displays the results of applicant's
 * operations.
 * </p>
 *
 */

@Controller
public class RecruiterSpring {

	private RecruiterService recruiterService = new RecruiterServiceImpl();
	private ApplicantService applicantService = new ApplicantServiceImpl();


	@RequestMapping("/createRecruiter")
	public String insertRecruiter(Model model) {
		model.addAttribute("recruiterDTO", new RecruiterDTO());
		return "createRecruiter";
	}

	@PostMapping({ "/insertRecruiter" })
	public String createRecruiter(@ModelAttribute ("recruiterDTO") RecruiterDTO recruiterDTO, Model model, HttpServletRequest request) {

		try {
			String message = null;
			HttpSession session = request.getSession();
			
			recruiterDTO.setApplicants((List<ApplicantDTO>) session.getAttribute("applicantsDTO"));
			
			if (0 == recruiterDTO.getId()) {
				model.addAttribute("recruiterDTO", recruiterService.createRecruiter(recruiterDTO));
				message = "Insert Successfully";
				// message = recruiterService.updateRecruiter(recruiter) ? "Update Successfully"
				// : "Update UnSuccessfull";
			} else {
				model.addAttribute("recruiterDTO", recruiterService.updateRecruiterById(recruiterDTO));
				message = "Update Successfully";
			}
			model.addAttribute("message", message);
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "createRecruiter";
	}

	/**
	 * <p>
	 * To display all the recruiters stored in the recruiters table. if the
	 * recruiters table is empty, display no record found.
	 * </p>
	 */
	@GetMapping("/displayRecruiters")
	private String displayRecruiters(Model model) {

		try {
			List<RecruiterDTO> recruitersDTO = recruiterService.displayRecruiters();
			model.addAttribute("recruitersDTO", recruitersDTO);

			if (!recruitersDTO.isEmpty()) {
				model.addAttribute("message", recruitersDTO);
			} else {
				model.addAttribute("message", "No Record Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}

		return "displayRecruiters";
	}

	@PostMapping({ "/getRecruiterById" })
	private String getRecruiterById(@RequestParam("recruiterId") int recruiterId, Model model,
			HttpServletRequest request) {
		try {
			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
			
			model.addAttribute("recruiterDTO", recruiterDTO);
			if (null != recruiterDTO) {
				model.addAttribute("recruiterDTO", recruiterDTO);	
			} else {
				model.addAttribute("Message", "No Record Found");
			}			
			//request.getSession().setAttribute("recruitersDTO", recruiterDTO.getApplicants());
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "getRecruiterById";
	}

	@PostMapping("/removeRecruiter")
	private String removeRecruiterById(@RequestParam("recruiterId") int recruiterId, Model model) {
		try {
			if (recruiterService.isIdExist(recruiterId)) {
				if (recruiterService.removeRecruiterById(recruiterId)) {
					model.addAttribute("message", "Deleted Successfully");
				} else {
					model.addAttribute("message", "Deletion Unsuccessfull");
				}
			} else {
				model.addAttribute("message", "Recruiter Not Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "removeRecruiter";
	}

	/*	*//**
			 * <p>
			 * To assign the recruiters for recruiter.
			 * </p>
			 *//*
				 * @PostMapping("/assignRecruiters") private String
				 * assignRecruiters(@RequestParam int id, Model model, HttpServletRequest
				 * request) { String view = null; try { HttpSession session =
				 * request.getSession(); boolean isPresent = recruiterService.isIdExist(id);
				 * session.setAttribute("recruiterId", id); if (isPresent) {
				 * model.addAttribute("isPresent", isPresent); view = "assignRecruiters"; } else
				 * { model.addAttribute("message", "recruiter not found"); view = "error"; } }
				 * catch (HireWorldException hireWorldException) {
				 * HireWorldLogger.displayError(hireWorldException.getMessage()); } return view;
				 * }
				 */

	/**
	 * <p>
	 * To get the recruiters by Applicant id and display the recruiters.
	 * </p>
	 */
	@GetMapping("/getRecruitersByApplicantId")
	private String getApplicantsByRecruiterId(@RequestParam("applicantId") int applicantId, Model model,
			HttpServletRequest request) {
		String view = null;
		try {
			List<RecruiterDTO> recruitersDTO = recruiterService.getRecruitersByApplicantId(applicantId);
			if (!recruitersDTO.isEmpty()) {
				model.addAttribute("recruitersDTO", recruitersDTO);
				view = "displayRecruiters";
			} else {
				model.addAttribute("error", "Recruiters Not found");
				view = "error";
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return view;
	}

	@PostMapping({ "/updateRecruiter" })
	private String updateRecruiterById(@RequestParam("recruiterId") int recruiterId, Model model,
			HttpServletRequest request) {

		/*
		 * try { String message = null; RecruiterDTO recruiterDTO =
		 * recruiterService.getRecruiterById(recruiterId);
		 * 
		 * if (recruiterService.isIdExist(recruiterId)) { if
		 * (recruiterService.updateRecruiterById(recruiterDTO)) {
		 * model.addAttribute("message", "Updation Successfully"); } else {
		 * model.addAttribute("message", "Updation Unsuccessfull"); } } else {
		 * model.addAttribute("message", "Recruiter Not Found"); }
		 * model.addAttribute("message", message);
		 */
			try {
				HttpSession session = request.getSession();
				RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
				session.setAttribute("applicantsDTO", recruiterDTO.getApplicants());
				if (recruiterService.isIdExist(recruiterId)) {
					model.addAttribute("recruiterDTO", recruiterDTO);
				} else {
					model.addAttribute("message", "Recruiter Not Found");
				}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "createRecruiter";
	}
	
	
	@PostMapping({ "/assignApplicant" })
	private String assignApplicant(@RequestParam("recruiterId") int recruiterId,
			@RequestParam("applicantId") int applicantId, Model model, HttpServletRequest request) {
		try {

			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);

			if (recruiterDTO != null) {
				List<ApplicantDTO> applicantsDTO = recruiterDTO.getApplicants();

				ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
				if (applicantDTO != null) {
					applicantsDTO.add(applicantDTO);
					recruiterDTO.setApplicants(applicantsDTO);

					if (recruiterService.updateRecruiterById(recruiterDTO) != null) {
						model.addAttribute("status", "Successfully Assigned");
					} else {
						model.addAttribute("status", "Recruiter Not Assigned");
					}
				} else {
					model.addAttribute("status", "Applicant Not Found");
				}
			} else {
				model.addAttribute("status", "Recruiter Not Found");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
		return "assignApplicant";
	}
	
	@PostMapping({ "/unAssignApplicant" })
	private String unAssignApplicant(@RequestParam("recruiterId") int recruiterId,
			@RequestParam("applicantId") int applicantId, Model model, HttpServletRequest request) {

		try {
			
			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
			HttpSession session = request.getSession();

			if (recruiterDTO != null) {
				
				ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
				List<ApplicantDTO> applicantsDTO = recruiterDTO.getApplicants();
				if (applicantDTO != null) {
					if (applicantsDTO.isEmpty()) {
						model.addAttribute("emptyList", "List is Empty");
					} else {
						for (int i = 0; i < applicantsDTO.size(); i++) {
							if (applicantsDTO.get(i).getId() == applicantId) {
								applicantsDTO.remove(applicantsDTO.get(i));
							} else {
								model.addAttribute("message", "Applicant not found...!");
							}
						}
						recruiterDTO.setApplicants(applicantsDTO);

						if (recruiterService.updateRecruiterById(recruiterDTO) != null) {
							model.addAttribute("message", " Successfully UnAssigned...!");
						} else {
							model.addAttribute("message", "Failed To UnAssigned....!");
						}
					}
				} else {
					model.addAttribute("message", "Applicant not found...!");
				}
			} else {
				model.addAttribute("message", "Recruiter not found...!");
			}
		} catch (HireWorldException hireWorldException) {
			HireWorldLogger.displayError(hireWorldException.getMessage());
		}
	    return "unAssignApplicant";
    }
}