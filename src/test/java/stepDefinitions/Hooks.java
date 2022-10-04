package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

import static stepDefinitions.StepDefinition.repoLinkExtension;

public class Hooks {

    @Before("@CheckRepoNoAuth")
    public void beforeScenario() throws IOException {

        StepDefinition m = new StepDefinition();
        repoLinkExtension = "OsmanEgretli/Repo";
        m.user_calls_an_http_request_with("DeleteRepoAPI", "Auth");
        m.repo_payload_creation_with("Repo", "Public repo with no auth Check", "False");
        m.user_calls_an_http_request_with("CreateRepoAPI", "Auth");
    }
}
