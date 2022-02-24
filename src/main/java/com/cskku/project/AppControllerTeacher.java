package com.cskku.project;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Controller
@RequestMapping("/teacher")
public class AppControllerTeacher {

	@Autowired
	private Teacher teacher;

	@Autowired
	private ClassRoom classRoom;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private ClassRoomRepository classRoomRepository;

	@Autowired
	private LaboratoryRepository laboratoryRepository;

	@Autowired
	private Laboratory laboratory;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TestCase testCase;
	
	@Autowired
	private TestCaseRepository testCaseRepository;

	@Autowired
	private Input input;
	
	@Autowired
	private InputRepository inputRepository;

	@Autowired
	private LabDetail labDetail;
	
	@Autowired
	private LabDetailRepository labDetailRepository;
	
	@Autowired
	private Document document;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private WorkRepository workRepository;
	
	

	private Authentication _getAunthenicatuin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
	private boolean _isTeacherAuthentication(String teacher_id) {
		Authentication auth = _getAunthenicatuin();
		String auth_teacher_id = auth.getName();
		return teacher_id.equals(auth_teacher_id);
	}

	@GetMapping("/")
	public String defaultPage(Model model) {
		return home(model);
	}

	@GetMapping("/home")
	public String home(Model model) {
		Authentication auth = _getAunthenicatuin();
		String teacher_id = auth.getName();
		Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
		model.addAttribute("teacher",teacher);
		return "TeacherHome";
	}

	@GetMapping("/login")
	public String login() {
		Authentication auth = _getAunthenicatuin();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/teacher/";
		}
		return "TeacherLogin";
	}

	@GetMapping("/register")
	public String register(Model model) {
		Authentication auth = _getAunthenicatuin();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/teacher/";
		}
		model.addAttribute("teacher", teacher);
		return "TeacherRegister";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teacher/login";
	}

	@GetMapping("/classRoom")
	public String classRoom(Model model) {
		Authentication auth = _getAunthenicatuin();
		String teacher_id = auth.getName();
		Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
		model.addAttribute("teacher",teacher);
		model.addAttribute("classRoom", classRoom);
		return "TeacherClassRoom";
	}

	@PostMapping("/classRoom/update")
	public String classRoomUpdate(@ModelAttribute("classRoom") ClassRoom classRoom) {
		Authentication auth = _getAunthenicatuin();
		String teacher_id = auth.getName();
		Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
		classRoom.setTeacher(teacher);
		classRoomRepository.save(classRoom);
		return "redirect:/teacher/classRoom";
	}

	@PostMapping("/classRoom/delete/{classRoom_id}")
	public String classRoomDelete(@PathVariable(name = "classRoom_id") Long classRoom_id) {
		ClassRoom classRoom = classRoomRepository.findById(classRoom_id).orElse(null);
		if(! _isTeacherAuthentication(classRoom.getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		classRoomRepository.delete(classRoom);
		return "redirect:/teacher/classRoom";
	}

	@GetMapping("/student/{classRoom_id}")
	public String classRoomStudent(@PathVariable(name = "classRoom_id") Long classRoom_id, Model model) {
		ClassRoom classRoom = classRoomRepository.findById(classRoom_id).orElse(null);
		if(! _isTeacherAuthentication(classRoom.getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("classRoom", classRoom);
		return "TeacherStudent";
	}

	@GetMapping("/laboratory/{classRoom_id}")
	public String laboratory(@PathVariable(name = "classRoom_id") Long classRoom_id, Model model) {
		ClassRoom classRoom = classRoomRepository.findById(classRoom_id).orElse(null);
		if(! _isTeacherAuthentication(classRoom.getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("classRoom", classRoom);
		model.addAttribute("laboratory", laboratory);
		return "TeacherLaboratory";
	}

	@PostMapping("/laboratory/update/{classRoom_id}")
	public String laboratoryUpdate(@PathVariable(name = "classRoom_id") Long classRoom_id, @ModelAttribute("laboratory") Laboratory laboratory) {
		ClassRoom classRoom = classRoomRepository.findById(classRoom_id).orElse(null);
		if(! _isTeacherAuthentication(classRoom.getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		laboratory.setClassRoom(classRoom);
		laboratoryRepository.save(laboratory);
		return "redirect:/teacher/laboratory/{classRoom_id}";
	}
	
	@PostMapping("/laboratory/delete/{laboratory_id}")
	public String laboratoryDelete(@PathVariable(name = "laboratory_id") Long laboratory_id) {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		laboratoryRepository.delete(laboratory);
		return "redirect:/teacher/laboratory/"+laboratory.getClassRoom().getClassRoom_id();
	}
	
	@GetMapping("/testCase/{laboratory_id}")
	public String testCase(@PathVariable(name = "laboratory_id") Long laboratory_id, Model model) {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("laboratory", laboratory);
		model.addAttribute("testCase", testCase);
		return "TeacherTestCase";
	}
	
	@PostMapping("/testCase/update/{laboratory_id}")
	public String testCaseUpdate(@PathVariable(name = "laboratory_id") Long laboratory_id,@ModelAttribute("testCase") TestCase testCase) {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		testCase.setLaboratory(laboratory);
		testCaseRepository.save(testCase);
		return "redirect:/teacher/testCase/{laboratory_id}";
	}

	@PostMapping("/testCase/delete/{testCase_id}")
	public String testCaseDelete(@PathVariable(name = "testCase_id") Long testCase_id) {
		TestCase testCase = testCaseRepository.findById(testCase_id).orElse(null);
		if(! _isTeacherAuthentication(testCase.getLaboratory().getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		testCaseRepository.delete(testCase);
		return "redirect:/teacher/testCase/"+testCase.getLaboratory().getLaboratory_id();
	}
	
	@GetMapping("/input/{testCase_id}")
	public String input(@PathVariable(name = "testCase_id") Long testCase_id, Model model) {
		TestCase testCase = testCaseRepository.findById(testCase_id).orElse(null);
		if(! _isTeacherAuthentication(testCase.getLaboratory().getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("testCase", testCase);
		model.addAttribute("input", input);
		return "TeacherInput";
	}
	
	@PostMapping("/input/update/{testCase_id}")
	public String inputUpdate(@PathVariable(name = "testCase_id") Long testCase_id,@ModelAttribute("input") Input input) {
		TestCase testCase = testCaseRepository.findById(testCase_id).orElse(null);
		if(! _isTeacherAuthentication(testCase.getLaboratory().getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		input.setTestCase(testCase);
		inputRepository.save(input);
		return "redirect:/teacher/input/{testCase_id}";
	}

	@PostMapping("/input/delete/{input_id}")
	public String inputDelete(@PathVariable(name = "input_id") Long input_id) {
		Input input = inputRepository.findById(input_id).orElse(null);
		if(! _isTeacherAuthentication(input.getTestCase().getLaboratory().getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		inputRepository.delete(input);
		return "redirect:/teacher/input/"+input.getTestCase().getTestCase_id();
	}
	
	@GetMapping("/labDetail/{laboratory_id}")
	public String labDetail(@PathVariable(name = "laboratory_id") Long laboratory_id, Model model) {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("laboratory", laboratory);
		model.addAttribute("labDetail", labDetail);
		return "TeacherLabDetail";
	}
	
	@PostMapping("/labDetail/update/{laboratory_id}")
	public String labDetailUpdate(@PathVariable(name = "laboratory_id") Long laboratory_id,@RequestParam("document") MultipartFile mutipartFile) throws IOException {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		String fileName = StringUtils.cleanPath(mutipartFile.getOriginalFilename());
		document.setDocument_id(null);
		document.setName(fileName);
		document.setContent(mutipartFile.getBytes());
		document.setSize(mutipartFile.getSize());
		document.setUpload_time(new Date());
		document = documentRepository.save(document);
		labDetail.setDocument(document);
		labDetail.setLaboratory(laboratory);
		labDetail.setLabDetail_id(null);
		labDetailRepository.save(labDetail);
		return "redirect:/teacher/labDetail/{laboratory_id}";
	}
	
	@PostMapping("/labDetail/delete/{labDetail_id}")
	public String labDetailDelete(@PathVariable(name = "labDetail_id") Long labDetail_id) {
		LabDetail labDetail = labDetailRepository.findById(labDetail_id).orElse(null);
		if(! _isTeacherAuthentication(labDetail.getLaboratory().getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		labDetailRepository.delete(labDetail);
		return "redirect:/teacher/labDetail/"+labDetail.getLaboratory().getLaboratory_id();
	}

	@GetMapping("/work/{laboratory_id}")
	public String work(@PathVariable(name = "laboratory_id") Long laboratory_id, Model model) {
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		if(! _isTeacherAuthentication(laboratory.getClassRoom().getTeacher().getTeacher_id())) {
			return "redirect:/403";
		}
		model.addAttribute("laboratory", laboratory);
		return "TeacherWork";
	}
	
	@GetMapping("/work/download/{work_id}")
	public void labDetailDownload(@PathVariable(name = "work_id") Long work_id, HttpServletResponse response) throws Exception {
		Work work = workRepository.findById(work_id).orElse(null);
		Document document = documentRepository.findById(work.getDocument().getDocument_id()).orElse(null);
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + document.getName();

		response.setHeader(headerKey, headerValue);

		ServletOutputStream outputStream = response.getOutputStream();

		outputStream.write(document.getContent());
		outputStream.close();
	}

}
