
Feature: Create,Read and Delete a Repo with authorization

  @CRDScenarios
  Scenario Outline: Create,Read and Delete a Repo with and without authorization
    Given Repository payload creation with "<Name>" "<Description>" "<Private>"
    When  User calls "CreateRepoAPI" request with "<AuthType>"
    Then  Verify api call got success with 201 status
    Then Verify Response body's "name" variable's value is "<Name>"
    When  User calls "GetRepoAPI" request with "<AuthType>"
    Then  Verify api call got success with 200 status
    When  User calls "DeleteRepoAPI" request with "<AuthType>"
    Then  Verify api call got success with 204 status

    Examples:
    (Name,Description, Private,AuthType)
      | Name    | Description  | Private | AuthType |
      | Deneme1 | deneme1desc  | True    | Auth     |
      | Deneme2 | deneme2desc  | False   | Auth     |
    #There are two keywords for handling the authorization
    #Type "Auth" for Authorized Session
    #Type "No Auth" for Unauthorized Session

  @CheckRepoNoAuth
    Scenario: Check if we can read and delete a created public Repo without auth
    Given User calls "GetRepoAPI" request with "No Auth"
    Then  Verify api call got success with 200 status
    Then User calls "DeleteRepoAPI" request with "No Auth"
    Then  Verify api call got success with 403 status
    Then Verify Response body's "message" variable's value is "Must have admin rights to Repository."


