//package org.tsa.hms_backend.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.tsa.hms_backend.entities.*;
//import org.tsa.hms_backend.enums.BloodGroup;
//import org.tsa.hms_backend.enums.Gender;
//import org.tsa.hms_backend.enums.Role;
//import org.tsa.hms_backend.repositories.*;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//@Configuration
//@AllArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final DoctorRepository doctorRepository;
//    private final PatientRepository patientRepository;
//    private final AppointmentRepository appointmentRepository;
//    private final DoctorScheduleRepository doctorScheduleRepository;
//    private final AnalysisTypeRepository analysisTypeRepository;
//    private final AnalysisRepository analysisRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Doctors doctor = saveDoctor();
//        Patients patient = savePatient();
//        saveDoctorSchedule(doctor);
//        saveAppointment(doctor, patient);
//        AnalysisType analysisType = saveAnalysisType();
//        saveAnalysis(doctor, patient, analysisType);
//        System.out.println("📦 Sample data loaded successfully.");
//    }
//
//    private Doctors saveDoctor(){
//        Users doctorUser = new Users();
//        doctorUser.setFirstName("Sanjar");
//        doctorUser.setLastName("Totliboyev");
//        doctorUser.setEmail("sanjar4@gmail.com");
//        doctorUser.setPassword(passwordEncoder.encode("pass123"));
//        doctorUser.setDateOfBirth(LocalDate.of(2003, 06, 04));
//        doctorUser.setGender(Gender.MALE);
//        doctorUser.setPhone("+998901234567");
//        doctorUser.setRole(Role.DOCTOR);
//
//        Doctors doctor = new Doctors();
//        doctor.setUser(doctorUser);
//        doctor.setDepartment("Cardiology");
//        doctor.setSpecialization("Cardiologist");
//        doctor.setCareerStartYear(2008);
//        doctor.setRoom("201");
//        doctor.setAddress("Main Street 10");
//        doctor.setAppointCost(200);
//        doctor.setAbout("Specialist in heart diseases.");
//        doctor.setImgLink("link-to-image.jpg");
//
//        return doctorRepository.save(doctor);
//    }
//
//    private Patients savePatient(){
//        Users patientUser = new Users();
//        patientUser.setFirstName("Akilhan");
//        patientUser.setLastName("Dadakhanov");
//        patientUser.setEmail("hms@gmail.com");
//        patientUser.setPassword("secure123");
//        patientUser.setDateOfBirth(LocalDate.of(2003, 3, 15));
//        patientUser.setGender(Gender.MALE);
//        patientUser.setPhone("+998909876543");
//        patientUser.setRole(Role.PATIENT);
//
//        Patients patient = new Patients();
//        patient.setUser(patientUser);
//        patient.setAddress("Park Avenue 5");
//        patient.setBloodGroup(BloodGroup.O_POSITIVE);
//
//        return patientRepository.save(patient);
//    }
//
//    private void saveDoctorSchedule(Doctors doctor){
//        DoctorSchedule schedule = new DoctorSchedule();
//        schedule.setDoctor(doctor);
//        schedule.setScheduleDate(LocalDate.now().plusDays(1));
//        schedule.setStartTime(LocalTime.of(9, 0));
//        schedule.setEndTime(LocalTime.of(12, 0));
//
//        doctorScheduleRepository.save(schedule);
//    }
//
//    private Appointments saveAppointment(Doctors doctor, Patients patient){
//        Appointments appointment = new Appointments();
//        appointment.setDoctor(doctor);
//        appointment.setPatient(patient);
//        appointment.setDate(LocalDate.now().plusDays(2));
//        appointment.setStartTime(LocalTime.of(10, 0));
//        appointment.setEndTime(LocalTime.of(10, 30));
//        appointment.setIsConfirmed(true);
//
//        return appointmentRepository.save(appointment);
//    }
//
//    private AnalysisType saveAnalysisType(){
//        AnalysisType analysisType = new AnalysisType();
//        analysisType.setName("Blood Test");
//        analysisType.setDescription("General blood panel test.");
//
//        return analysisTypeRepository.save(analysisType);
//    }
//
//    private void saveAnalysis(Doctors doctor, Patients patient, AnalysisType analysisType){
//        Analysis analysis = new Analysis();
//        analysis.setDoctor(doctor);
//        analysis.setPatients(patient);
//        analysis.setAnalysisType(analysisType);
//        analysis.setName("Full CBC");
//        analysis.setDescription("Complete Blood Count");
//        analysis.setPrice(50);
//        analysis.setRoom("Lab 3");
//
//        analysisRepository.save(analysis);
//    }
//}
//
