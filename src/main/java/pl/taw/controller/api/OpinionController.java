package pl.taw.controller.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.taw.controller.business.OpinionService;
import pl.taw.controller.business.VisitService;
import pl.taw.controller.dao.DoctorDAO;
import pl.taw.controller.dao.PatientDAO;
import pl.taw.controller.dto.DoctorDTO;
import pl.taw.controller.dto.OpinionDTO;
import pl.taw.controller.dto.PatientDTO;
import pl.taw.controller.dto.VisitDTO;
import pl.taw.infrastructure.database.entity.*;
import pl.taw.infrastructure.database.repository.OpinionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(OpinionController.OPINIONS)
@AllArgsConstructor
public class OpinionController {

    public static final String OPINIONS = "/opinions";
    public static final String DOCTOR_ID = "/doctor/{doctorId}";
    public static final String PATIENT_ID = "/patient/{patientId}";
    public static final String ADD = "/add/{visitId}";
    //    public static final String ADD = "/add";
    public static final String EDIT = "/edit/{opinionId}";


    private final OpinionService opinionService;
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
//    private final VisitDTO visitDTO;
    private final VisitService visitService;
    private final OpinionRepository opinionRepository;


    @GetMapping
    public String opinionsPage() {
        return "opinions";
    }

    // wyświetlanie opinii lekarza
    @GetMapping(DOCTOR_ID)
    public String showDoctorOpinions(@PathVariable("doctorId") Integer doctorId, Model model) {
        List<OpinionDTO> opinions = opinionService.getDoctorOpinions(doctorId);
        DoctorDTO doctor = doctorDAO.findById(doctorId);

        model.addAttribute("opinions", opinions);
        model.addAttribute("doctor", doctor);

        return "doctor-opinions"; // Nazwa widoku, do którego zostaną przekazane opinie.
    }

    // wyświetlanie opinii konkretnego pacjenta
    @GetMapping(PATIENT_ID)
    public String showPatientOpinions(@PathVariable("patientId") Integer patientId, Model model) {
        List<OpinionDTO> opinions = opinionService.getPatientOpinions(patientId);
        PatientDTO patient = patientDAO.findById(patientId);

        model.addAttribute("opinions", opinions);
        model.addAttribute("patient", patient);

        return "patient-opinions";
    }

    // !!! nie działa
    // dodawanie opinii przez pacjenta
//    @PostMapping(ADD)
//    @PostMapping("/add/{visitId}")
    @PostMapping(OPINIONS + ADD)
    public String addOpinion(@PathVariable("visitId") Integer visitId, Model model) {
        // Przygotowanie modelu dla formularza dodawania opinii
        OpinionDTO opinionDTO = new OpinionDTO();

        model.addAttribute("opinionDTO", opinionDTO);
        model.addAttribute("visitId", visitId);

        System.out.println("addOpinion zostało wywołane");

        return "opinion-form"; // Nazwa widoku z formularzem dodawania opinii
    }

    @PutMapping(EDIT)
    public String editOpinion(@PathVariable("opinionId") Integer opinionId,
                              @ModelAttribute("opinionDTO")
                              @Valid OpinionDTO opinionDTO,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            // Obsługa błędów walidacji
            model.addAttribute("opinionId", opinionId);
            return "opinion-form"; // Powrót do formularza z błędami
        }

        // Zaktualizuj opinię w serwisie lub repozytorium
        opinionService.updateOpinion(opinionDTO);

        // Przekierowanie na stronę z listą opinii dla lekarza lub pacjenta
        if (opinionDTO.getVisitId() != null) {
            // Jeśli opinia została dodana w kontekście konkretnej wizyty, przekieruj na stronę z listą opinii dla lekarza
            return "redirect:/doctors/" + opinionDTO.getDoctorId() + "/opinions";
        } else {
            // Jeśli opinia została dodana niezależnie od wizyty, przekieruj na stronę z listą opinii dla pacjenta
            return "redirect:/patients/" + opinionDTO.getPatientId() + "/opinions";
        }
    }


//    @PostMapping
//    public String saveOpinion(@ModelAttribute("opinionDTO") @Valid OpinionDTO opinionDTO, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            // Obsługa błędów walidacji
//            model.addAttribute("visitId", opinionDTO.getVisit().getVisitId());
//            return "opinion-form"; // Powrót do formularza z błędami
//        }
//
//        // Zapis lub aktualizacja opinii w serwisie lub repozytorium
//        if (opinionDTO.getOpinionId() != null) {
//            opinionService.updateOpinion(opinionDTO);
//        } else {
//            opinionService.addOpinion(opinionDTO);
//        }
//
//        // Przekierowanie na stronę z listą opinii dla lekarza lub pacjenta
//        if (opinionDTO.getVisit().getVisitId() != null) {
//            // Jeśli opinia została dodana w kontekście konkretnej wizyty, przekieruj na stronę z listą opinii dla lekarza
//            return "redirect:/doctors/" + opinionDTO.getDoctor().getDoctorId() + "/opinions";
//        } else {
//            // Jeśli opinia została dodana niezależnie od wizyty, przekieruj na stronę z listą opinii dla pacjenta
//            return "redirect:/patients/" + opinionDTO.getPatient().getPatientId() + "/opinions";
//        }
//    }


    @GetMapping("/addForm")
    public String showAddOpinionForm(Model model) {
        model.addAttribute("opinionDTO", new OpinionDTO());
        return "opinion-form"; // Nazwa widoku z formularzem dodawania opinii
    }


    @PostMapping("/dodaj")
    public String dodajOpinie(@ModelAttribute("opinionDTO") OpinionDTO opinionDTO) {
        // Wykonaj operacje związane z dodawaniem opinii do bazy danych
        opinionService.addOpinion(opinionDTO);

        // Przekieruj na odpowiedni widok
        return "success"; // Możesz zastąpić "success" nazwą widoku, który ma zostać wyświetlony po dodaniu opinii
    }

    // wzór dla posta
    @PostMapping("/opinions/addNieMaTakiego")
    public String addOpinion(@ModelAttribute("opinionDTO") OpinionDTO opinionDTO, @RequestParam("visitId") Integer visitId) {
        // Przekształcenie visitId na obiekt VisitEntity
        VisitEntity visit = new VisitEntity();
        visit.setVisitId(visitId);
        opinionDTO.setVisitId(visitId);

        // Dodanie opinii do bazy danych

        return "redirect:/opinions";
    }

    @GetMapping("/dodaj-opinie")
    public String showOpinionForm(Model model) {
//        List<DoctorEntity> doctors = doctorService.getAllDoctors();
//        List<PatientEntity> patients = patientService.getAllPatients();
//        List<VisitEntity> visits = visitService.getAllVisits();
//
//        model.addAttribute("doctors", doctors);
//        model.addAttribute("patients", patients);
//        model.addAttribute("visits", visits);

        model.addAttribute("opinionDTO", new OpinionDTO());

        return "opinion-form";
    }

    @PostMapping("/dodaj-opinie")
    public String addOpinion(@ModelAttribute OpinionDTO opinionDTO) {
        // Logika dodawania opinii

        return "redirect:/sukces";
    }

//    @GetMapping("/dodaj-opinie/{patientId}")
//    public String showOpinionForm(@PathVariable("patientId") Integer patientId, Model model) {
//        PatientDTO patient = patientDAO.findById(patientId);
//        List<VisitDTO> visits = visitService.getPatientVisits(patientId).stream()
//                .filter(visit -> visit.getOpinion() == null)
//                .toList();
//
//        // Pobierz pierwszą wizytę z listy, jeśli istnieje
//        VisitDTO visit = visits.isEmpty() ? null : visits.get(0);
//
//        model.addAttribute("patient", patient);
//        model.addAttribute("visit", visit);
//        model.addAttribute("opinionDTO", new OpinionDTO());
//
//        return "opinion-dodaj";
//    }

    @PostMapping("/zapisz-opinie")
    public String zapiszOpinie(@ModelAttribute OpinionDTO opinionDTO) {
        log.debug("Przetwarzanie żądania zapisu opinii");
        OpinionEntity opinionEntity = new OpinionEntity();
        opinionEntity.setDoctorId(opinionDTO.getDoctorId());
        opinionEntity.setPatientId(opinionDTO.getPatientId());
        opinionEntity.setComment(opinionDTO.getComment());
        opinionEntity.setCreatedAt(LocalDateTime.now());
        opinionEntity.setVisitId(opinionDTO.getVisitId());

        // Zapisanie opinii do bazy danych
        opinionRepository.save(opinionEntity);

        return "success";
    }

    // z home.html przekierowuje tutaj
//    @GetMapping("/dodaj")
//    public String dodaj(Model model) {
//        // dodanie pacjenta i doktora na sztywno
//        PatientEntity patient = patientDAO.findById(5);
//        DoctorEntity doctor = doctorDAO.findById(2);
//
//        model.addAttribute("patient", patient);
//        model.addAttribute("doctor", doctor);
//        model.addAttribute("opinion", new OpinionEntity());
//        return "dodaj";
//    }

    @GetMapping("/dodaj")
    public ModelAndView dodaj() {
        // dodanie pacjenta i doktora na sztywno
        PatientEntity patient = patientDAO.findById(5);
        DoctorEntity doctor = doctorDAO.findEntityById(2);

        ModelAndView modelAndView = new ModelAndView("dodaj");
        modelAndView.addObject("patient", patient);
        modelAndView.addObject("doctor", doctor);
        modelAndView.addObject("opinion", new OpinionEntity());

        return modelAndView;
    }


    @PostMapping("/zapiszXXX")
    public String zapisz(@ModelAttribute OpinionEntity opinion) {
        opinionRepository.save(opinion);
        return "redirect:/dodaj";
    }

    @PostMapping("/zapisz")
    public String zapiszOpinie(@RequestParam Integer patientId, @RequestParam Integer doctorId, @RequestParam String comment) {
        // Tworzenie nowej opinii
        OpinionEntity opinionEntity = OpinionEntity.builder()
                .doctorId(doctorId)
                .patientId(patientId)
                .comment(comment)
                .createdAt(LocalDateTime.now())
                .build();

        opinionRepository.save(opinionEntity);

        // Przekierowanie na stronę sukcesu lub inną stronę
        return "success";
    }


}
