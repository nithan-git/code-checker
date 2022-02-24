package com.cskku.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
public class AppControllerStudent {

	@Autowired
	private Student student;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ClassRoomRepository classRoomRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private Register register;
	
	@Autowired
	private WorkRepository workRepository;
	
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private Work work;

	@Autowired
	private Document document;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private LabDetailRepository labDetailRepository;

	private Authentication _getAunthenicatuin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
	private boolean _isStudentAuthentication(String stduent_id) {
		Authentication auth = _getAunthenicatuin();
		String auth_student_id = auth.getName();
		return stduent_id.equals(auth_student_id);
	}
	
	@GetMapping("/")
	public String defaultPage(Model model) {
		return home(model);
	}

	@GetMapping("/home")
	public String home(Model model) {
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		model.addAttribute("student",student);
		return "StudentHome";
	}

	@GetMapping("/login")
	public String login() {
		Authentication auth = _getAunthenicatuin();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/student/";
		}
		return "StudentLogin";
	}


	@GetMapping("/register")
	public String register(Model model) {
		Authentication auth = _getAunthenicatuin();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/student/";
		}
		model.addAttribute("student", student);
		return "StudentRegister";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/student/login";
	}
	
	@GetMapping("/registerClassRoom")
	public String registerClassRoom(Model model) {
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		Set<Register> registeds = student.getRegisters();
		Iterable<ClassRoom> AllclassRooms = classRoomRepository.findAll();
		List<Long> classRoomIDRegisteds = new ArrayList<>();
		for(var registed:registeds) {
			classRoomIDRegisteds.add(registed.getClassRoom().getClassRoom_id());
		}
		List<ClassRoom> classRooms = new ArrayList<>();
		for(var classRoom:AllclassRooms) {
			if (classRoomIDRegisteds.contains(classRoom.getClassRoom_id())) {
				continue;
			}
			classRooms.add(classRoom);
		}
		model.addAttribute("classRooms", classRooms);
		model.addAttribute("student", student);
		return "StudentRegisterClassRoom";
	}
	
	@PostMapping("/registerClassRoom/{classRoom_id}")
	public String registerClassRoom(@PathVariable(name = "classRoom_id") Long classRoom_id) {
		ClassRoom classRoom = classRoomRepository.findById(classRoom_id).orElse(null);
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		register.setRegister_id(null);
		register.setClassRoom(classRoom);
		register.setStudent(student);
		registerRepository.save(register);
		return "redirect:/student/registerClassRoom";
	}
	
	@GetMapping("/classRoom")
	public String classRoom(Model model) {
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		model.addAttribute("student", student);
		return "StudentClassRoom";
	}
	
	@PostMapping("/withdrawClassRoom/{register_id}")
	public String registerClassRoomWithdraw(@PathVariable(name = "register_id") Long register_id) {
		Register register = registerRepository.findById(register_id).orElse(null);
		if(! _isStudentAuthentication(register.getStudent().getStudent_id())) {
			return "redirect:/403";
		}
		registerRepository.delete(register);
		return "redirect:/student/classRoom";
	}

	@GetMapping("/laboratory/{register_id}")
	public String laboratory(@PathVariable(name = "register_id") Long register_id,Model model) {
		Register register = registerRepository.findById(register_id).orElse(null);
		if(! _isStudentAuthentication(register.getStudent().getStudent_id())) {
			return "redirect:/403";
		}
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		Set<Laboratory> laboratorys = register.getClassRoom().getLaboratorys();
		for(var laboratory:laboratorys) {
			Set<Work> works = new HashSet<>();
			Work work = workRepository.findByStudentAndLaboratory(student, laboratory);
			if (work !=null) {
				works.add(work);
				
			}
			laboratory.setWorks(works);
		}
		ClassRoom classRoom = register.getClassRoom();
		classRoom.setLaboratorys(laboratorys);
		model.addAttribute("classRoom", classRoom);
		return "StudentLaboratory";
	}
	
	@PostMapping("/work/update/{register_id}/{laboratory_id}")
	public String workUpdate(@PathVariable(name = "register_id") Long register_id,@PathVariable(name = "laboratory_id") Long laboratory_id,@RequestParam("document") MultipartFile mutipartFile) throws IOException {
		Register register = registerRepository.findById(register_id).orElse(null);
		if(! _isStudentAuthentication(register.getStudent().getStudent_id())) {
			return "redirect:/403";
		}
		Laboratory laboratory = laboratoryRepository.findById(laboratory_id).orElse(null);
		Authentication auth = _getAunthenicatuin();
		String student_id = auth.getName();
		Student student = studentRepository.findById(student_id).orElse(null);
		Work work = workRepository.findByStudentAndLaboratory(student, laboratory);
		if (work==null) {
			work = this.work;
			work.setWork_id(null);
			work.setIsCorrect(false);
			work.setLaboratory(laboratory);
			work.setStudent(student);
			work.setDocument(null);
		}
		String fileName = StringUtils.cleanPath(mutipartFile.getOriginalFilename());
		if (work.getDocument()==null) {
			document.setDocument_id(null);
		}else {
			document.setDocument_id(work.getDocument().getDocument_id());
		}
		document.setName(fileName);
		document.setContent(mutipartFile.getBytes());
		document.setSize(mutipartFile.getSize());
		document.setUpload_time(new Date());
		document = documentRepository.save(document);


		work.setDocument(document);
		workRepository.save(work);
		return "redirect:/student/laboratory/{register_id}";
	}
	
	@GetMapping("/labDetail/download/{labDetail_id}")
	public void labDetailDownload(@PathVariable(name = "labDetail_id") Long labDetail_id, HttpServletResponse response) throws Exception {
		LabDetail labDetail = labDetailRepository.findById(labDetail_id).orElse(null);
		Document document = documentRepository.findById(labDetail.getDocument().getDocument_id()).orElse(null);
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + document.getName();

		response.setHeader(headerKey, headerValue);

		ServletOutputStream outputStream = response.getOutputStream();

		outputStream.write(document.getContent());
		outputStream.close();
	}
	
	@PostMapping("/work/check/{register_id}/{work_id}")
	public String downRun(@PathVariable(name = "register_id") Long register_id,@PathVariable(name = "work_id") Long work_id) {
		Work work = workRepository.findById(work_id).orElse(null);
		boolean isCorrect = PythonChecker.check(work);
		work.setIsCorrect(isCorrect);
		workRepository.save(work);
		return "redirect:/student/laboratory/{register_id}";
	}
}
