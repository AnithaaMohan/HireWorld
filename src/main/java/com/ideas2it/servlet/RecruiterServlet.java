//package com.ideas2it.servlet;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.ideas2it.model.Applicant;
//import com.ideas2it.model.ApplicantDTO;
//import com.ideas2it.model.Recruiter;
//import com.ideas2it.model.RecruiterDTO;
//import com.ideas2it.service.ApplicantService;
//import com.ideas2it.service.RecruiterService;
//import com.ideas2it.service.impl.ApplicantServiceImpl;
//import com.ideas2it.service.impl.RecruiterServiceImpl;
//import com.ideas2it.util.DateUtil;
//import com.ideas2it.exception.HireWorldException;
//import com.ideas2it.logger.HireWorldLogger;
//
///**
// * Servlet implementation class Applicant
// 
//@WebServlet(urlPatterns = { "/createRecruiter", "/displayRecruiters", "/getRecruiterById", "/updateRecruiterById",
//		"/removeRecruiterById", "/getRecruitersByApplicantId", "/getRecruiterForUpdate", "/assignApplicant",
//		"/unAssignApplicant" })
//
//public class RecruiterServlet extends HttpServlet {
//
//	private RecruiterService recruiterService = new RecruiterServiceImpl();
//	private ApplicantService applicantService = new ApplicantServiceImpl();
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//			String action = request.getServletPath();
//
//			switch (action) {
//			case "/createRecruiter":
//				createRecruiter(request, response);
//				break;
//			case "/displayRecruiters":
//				displayRecruiters(request, response);
//				break;
//			case "/getRecruiterById":
//				getRecruiterById(request, response);
//				break;
//			case "/updateRecruiterById":
//				updateRecruiterById(request, response);
//				break;
//			case "/getRecruiterForUpdate":
//				getRecruiterById(request, response);
//				break;
//			case "/removeRecruiterById":
//				removeRecruiterById(request, response);
//				break;
//			case "/getRecruitersByApplicantId":
//				getRecruitersByApplicantId(request, response);
//				break;
//			case "/assignApplicant":
//				assignApplicant(request, response);
//				break;
//			case "/unAssignApplicant":
//				unAssignApplicant(request, response);
//				break;
//			}
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//			String action = request.getServletPath();
//
//			switch (action) {
//			case "/createRecruiter":
//				createRecruiter(request, response);
//				break;
//			case "/displayRecruiters":
//				displayRecruiters(request, response);
//				break;
//			case "/getRecruiterById":
//				getRecruiterById(request, response);
//				break;
//			case "/updateRecruiterById":
//				updateRecruiterById(request, response);
//				break;
//			case "/getRecruiterForUpdate":
//				getRecruiterById(request, response);
//				break;
//			case "/removeRecruiterById":
//				removeRecruiterById(request, response);
//				break;
//			case "/getRecruitersByApplicantId":
//				getRecruitersByApplicantId(request, response);
//				break;
//			case "/assignApplicant":
//				assignApplicant(request, response);
//				break;
//			case "/unAssignApplicant":
//				unAssignApplicant(request, response);
//				break;
//			}
//	}
//
//	private void createRecruiter(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//
//		try {
//
//			String name = request.getParameter("name");
//			String emailAddress = request.getParameter("emailAddress");
//			Long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
//
//			RecruiterDTO recruiterDTO = new RecruiterDTO();
//
//			recruiterDTO.setName(name);
//			recruiterDTO.setMobileNumber(mobileNumber);
//			recruiterDTO.setEmailAddress(emailAddress);
//
//			RecruiterDTO createdRecruiterDTO = recruiterService.createRecruiter(recruiterDTO);
//
//			HttpSession session = request.getSession();
//
//			if (0 != createdRecruiterDTO.getId()) {
//				session.setAttribute("Recruiter", createdRecruiterDTO);
//			} else {
//				session.setAttribute("Recruiter", "Failed to Create Recruiter");
//			}
//			response.sendRedirect("createRecruiter.jsp");
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * <p>
//	 * To display all the Recruiters stored in the Recruiters table. if the
//	 * recruiters table is empty, display no record found.
//	 * </p>
//	 
//	private void displayRecruiters(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//
//			List<RecruiterDTO> recruitersDTO = recruiterService.displayRecruiters();
//			//System.out.println(recruitersDTO );
//			//HttpSession session = request.getSession();
//
//			if (!recruitersDTO.isEmpty()) {
//				request.setAttribute("Recruiters", recruitersDTO);
//			} else {
//				request.setAttribute("Recruiters", "No Record Found");
//			}
//			RequestDispatcher rs = request.getRequestDispatcher("displayRecruiters.jsp");
//			rs.forward(request, response);
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * Gets the Recruiter form database using Recruiter id
//	 *
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 * @throws HireWorldException
//	 
//	public void getRecruiterById(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		try {
//
//			int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
//			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
//
//			HttpSession session = request.getSession();
//			System.out.println(recruiterDTO);
//
//			if (null != recruiterDTO) {
//				session.setAttribute("recruiterDTO", recruiterDTO);
//				if (request.getServletPath().equals("/getRecruiterForUpdate")) {
//					response.sendRedirect("updateRecruiter.jsp");
//				} else {
//					response.sendRedirect("getRecruiterById.jsp");
//				}
//			}
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * Update the recruiter details form database based on the request
//	 *
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ParseException
//	 * @throws ServletException
//	 * @throws HireWorldException
//	 */
//	private void updateRecruiterById(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//
//		try {
//			int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
//			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
//
//			String name = request.getParameter("name");
//			String emailAddress = request.getParameter("emailAddress");
//			Long mobileNumber = Long.parseLong(request.getParameter("mobileNumber"));
//
//			recruiterDTO.setName(name);
//			recruiterDTO.setMobileNumber(mobileNumber);
//			recruiterDTO.setEmailAddress(emailAddress);
//
//			recruiterDTO = recruiterService.updateRecruiterById(recruiterDTO);
//			HttpSession session = request.getSession();
//			session.setAttribute("recruiterDTO", recruiterDTO);
//			/*
//			 * if (recruiterService.updateRecruiterById(recruiterDTO) != null) {
//			 * session.setAttribute("Recruiter", "Recruiter Successfully updated..."); }
//			 * else { session.setAttribute("Recruiter", "Recruiter not updated..."); }
//			 */
//			response.sendRedirect("updateRecruiter.jsp");
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * Delete the Recruiter form database based on the requested recruiter id
//	 *
//	 * @param request
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 * @throws HireWorldException
//	 */
//	private void removeRecruiterById(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//
//		try {
//			boolean isDeleted = recruiterService
//					.removeRecruiterById(Integer.parseInt(request.getParameter("recruiterId")));
//			HttpSession session = request.getSession();
//			session.setAttribute("isDeleted", isDeleted);
//			response.sendRedirect("removeRecruiter.jsp");
//
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/*
//	 * try { HttpSession session = request.getSession();
//	 * 
//	 * if (null != recruiterDTO) { boolean isDeleted =
//	 * recruiterService.removeRecruiterById(recruiterId);
//	 * 
//	 * if (isDeleted) { session.setAttribute("isDeleted",
//	 * "Recruiter Deleted Sucessfully");
//	 * response.sendRedirect("removeRecruiter.jsp"); } else {
//	 * session.setAttribute("isDeleted", "Failed to Delete Recruiter "); } } else {
//	 * session.setAttribute("isDeleted", "No Recruiters Found");
//	 * response.sendRedirect("No Applicant Found.jsp"); }
//	 * 
//	 * } catch (HireWorldException hireWorldException) {
//	 * HireWorldLogger.displayError(hireWorldException.getMessage()); } }
//	 */
//
//	private void getRecruitersByApplicantId(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		try {
//
//			int applicantId = Integer.parseInt(request.getParameter("applicantId"));
//			List<RecruiterDTO> recruitersDTO = recruiterService.getRecruitersByApplicantId(applicantId);
//			System.out.println(recruitersDTO);			
//
//			if (!recruitersDTO.isEmpty()) {
//				request.setAttribute("Recruiters", recruitersDTO);
//			} else {
//				request.setAttribute("text", "Recruiters Not found");
//			}
//			RequestDispatcher rs = request.getRequestDispatcher("displayRecruiters.jsp");
//			rs.forward(request, response);
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * <p>
//	 * To assign the Recruiter for Recruiter.
//	 * </p>
//	 */
//	private void assignApplicant(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println("called");
//		try {
//
//			int applicantId = Integer.parseInt(request.getParameter("applicantId"));
//			int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
//
//			HttpSession session = request.getSession();
//
//			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
//
//			if (recruiterDTO != null) {
//				List<ApplicantDTO> applicantsDTO = recruiterDTO.getApplicants();
//				ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
//				if (applicantDTO != null) {
//					applicantsDTO.add(applicantDTO);
//					recruiterDTO.setApplicants(applicantsDTO);
//
//					if (recruiterService.updateRecruiterById(recruiterDTO) != null) {
//						session.setAttribute("status", "Successfully Assigned");
//					} else {
//						session.setAttribute("status", "Recruiter Not Assigned");
//					}
//				} else {
//					session.setAttribute("status", "Applicant Not Found");
//				}
//			} else {
//				session.setAttribute("status", "Recruiter Not Found");
//			}
//
//			response.sendRedirect("assignApplicant.jsp");
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//
//	/**
//	 * Unassign applicants based on inputs
//	 *
//	 * @param request
//	 * @param response
//	 * @throws CustomException
//	 * @throws ServletException
//	 * @throws IOException
//	 */
//	private void unAssignApplicant(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("called");
//		try {
//
//			int recruiterId = Integer.parseInt(request.getParameter("recruiterId"));
//			int applicantId = Integer.parseInt(request.getParameter("applicantId"));
//			RecruiterDTO recruiterDTO = recruiterService.getRecruiterById(recruiterId);
//			HttpSession session = request.getSession();
//
//			if (recruiterDTO != null) {
//				ApplicantDTO applicantDTO = applicantService.getApplicantById(applicantId);
//				List<ApplicantDTO> applicantsDTO = recruiterDTO.getApplicants();
//				if (applicantDTO != null) {
//					if (applicantsDTO.isEmpty()) {
//						request.setAttribute("emptyList", "List is Empty");
//					} else {
//						for (int i = 0; i < applicantsDTO.size(); i++) {
//							if (applicantsDTO.get(i).getId() == applicantId) {
//								applicantsDTO.remove(applicantsDTO.get(i));
//							} else {
//								session.setAttribute("message", "Applicant not found...!");
//							}
//						}
//						recruiterDTO.setApplicants(applicantsDTO);
//
//						if (recruiterService.updateRecruiterById(recruiterDTO) != null) {
//							session.setAttribute("message", "Applicant Successfully Un Assigned...!");
//						} else {
//							session.setAttribute("message", "Applicant not Unassigned....!");
//						}
//					}
//				} else {
//					session.setAttribute("message", "Applicant not found...!");
//				}
//			} else {
//				session.setAttribute("message", "Recruiter not found...!");
//			}
//			response.sendRedirect("unAssignApplicant.jsp");
//		} catch (HireWorldException hireWorldException) {
//			HireWorldLogger.displayError(hireWorldException.getMessage());
//		}
//	}
//}
