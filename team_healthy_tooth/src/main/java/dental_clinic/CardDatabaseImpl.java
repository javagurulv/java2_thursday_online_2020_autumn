package dental_clinic;

import dental_clinic.domain.Card;
import dental_clinic.domain.PatientPersonalData;
import dental_clinic.domain.ToothInfo;
import dental_clinic.domain.ToothStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CardDatabaseImpl implements CardDatabase {

    private Long id= 1L;
    private List<Card> cardList = new ArrayList<>();


    @Override
    public boolean addPatient(PatientPersonalData patientPersonalData) {
        if (cardListContainsPatient(patientPersonalData)){
            return false;
        }else
            {
            patientPersonalData.setId(id);
            cardList.add(new Card(patientPersonalData));
            id++;
        }
        return true;
    }

    @Override
    public boolean deletePatient(long id) {
        for (int i = 0; i < cardList.size(); i++){
            if (cardList.get(i).getPatient().getId() == id){
                cardList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean printDatabase() {
        cardList.forEach(System.out::println);
        return !cardList.isEmpty();
    }

    @Override
    public boolean printPatientHistory(long id) {
            for (int i = 0; i < cardList.size(); i++){
                if (isSpecificPatient(i, id)){
                    System.out.println(cardList.get(i).toString());
                    return true;
                }
            }
        return false;
    }

    @Override
    public List<PatientPersonalData> findPatientBySurname(String surname) {
        return cardList.stream()
                .map(card -> card.getPatient())
                .filter(patientPersonalData -> patientPersonalData.getSurname().equalsIgnoreCase(surname))
                .collect(Collectors.toList());
    }

    @Override
    public List<PatientPersonalData> findPatientByPersonalCode(String personalCode) {
        return cardList.stream()
                .map(card -> card.getPatient())
                .filter(patientPersonalData -> patientPersonalData.getPersonalCode().equals(personalCode))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateJowlData(long id, int toothNumber, Optional<String> comment, ToothStatus toothStatus) {

        Optional <Card> patientsCard = cardList.stream()
                .filter(card -> card.getPatient().getId() == id)
                .findFirst();
        if (patientsCard.isPresent()){
            patientsCard.get().updateJowl(toothNumber, comment, toothStatus);
            updatePatientsCard(patientsCard.get(), id);
            if (patientsCard.get().getJowl().keySet().contains(toothNumber)) return true;
            else return false;
        }
        return false;
    }

    @Override
    public boolean printPatientCardForVisit(long id) {
        for (int i = 0; i < cardList.size(); i++){
            if (isSpecificPatient(i, id)){
                System.out.println(cardList.get(i).getPatient());
                printActualInfoAboutJowl(i);
                return true;
            }
        }
        return false;
    }

    private boolean cardListContainsPatient (PatientPersonalData patientPersonalData){
        Optional <PatientPersonalData> result = cardList.stream()
                .map(card -> card.getPatient())
                .filter(patient1 -> patient1.equals(patientPersonalData))
                .findAny();
        return result.isPresent();
    }

    private void updatePatientsCard(Card patientsCard, long id){
        for (int i = 0; i < cardList.size(); i++){
            if (cardList.get(i).getPatient().getId() == id){
                cardList.set(i, patientsCard);
            }
        }
    }

    private boolean cardListContainsId (long id){
        Optional <PatientPersonalData> result = cardList.stream()
                .map(card -> card.getPatient())
                .filter(patient1 -> patient1.getId() == id)
                .findAny();
        return result.isPresent();
    }

    private boolean isSpecificPatient (int index, long id) {
        return cardList.get(index).getPatient().getId() == id;
    }

    private void printActualInfoAboutJowl(int index){
        Map<Integer, ToothInfo> result = cardList.get(index).getJowl();
        String toothInfoForPrint = "";

        for (Integer key : result.keySet()){
            toothInfoForPrint += key + " ";
            if (result.get(key).getComment().size() > 0){
                if (!result.get(key).getComment().get(result.get(key).getComment().size()-1).equals(Optional.empty())){
                    toothInfoForPrint += result.get(key).getComment().get(result.get(key).getComment().size()-1) + " ";
                }
            }
            toothInfoForPrint += result.get(key).getStatus().get(result.get(key).getStatus().size()-1) + "\n";
        }

        System.out.println(toothInfoForPrint);
    }
}
