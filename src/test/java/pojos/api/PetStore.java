package pojos.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //it comes jackson
public class PetStore {


        public int id;
        public String name;
        public List<String> photoUrls;
        public String status;

    public PetStore() {
    }
    public PetStore(int id, String name, List<String> photoUrls, String status) {
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", status='" + status + '\'' +
                '}';
    }
}
