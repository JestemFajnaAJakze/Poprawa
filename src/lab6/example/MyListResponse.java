package lab6.example;

import lab6.rest.pojo.EntryPOJO;
import lab6.rest.pojo.SubstancePOJO;

import java.util.List;

public class MyListResponse {
    List<SubstancePOJO> substances;

    public List<SubstancePOJO> getSubstances() {
        return substances;
    }

    public void setSubstances(List<SubstancePOJO> substances) {
        this.substances = substances;
    }
}
