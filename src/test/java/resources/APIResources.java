package resources;

public enum APIResources {

    CreateRepoAPI("user/repos"),
    GetRepoAPI("repos/"),
    DeleteRepoAPI("repos/");

    private String resource;

    APIResources(String resource) {

        this.resource = resource;
    }

    public String getResource() {

        return resource;
    }

}
