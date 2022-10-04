package resources;

import models.CreateRepositoryBody;


public class TestDataBuilder {

    public CreateRepositoryBody createRepoPayload(String Name,String Description , Boolean isPrivate) {

        CreateRepositoryBody repoBody = new CreateRepositoryBody();
        repoBody.setDescription(Description);
        repoBody.setPrivate(isPrivate);
        repoBody.setName(Name);
        return repoBody;
    }
}
