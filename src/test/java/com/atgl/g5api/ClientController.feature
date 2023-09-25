Feature: Controller
  As a user, i want to test the API controller

  Scenario: Get request
    Given : I wanna start a browser
    When : I do a Get request at "http://localhost:9001/clients"
    Then : I receive "{\"id\":1,\"prenom\":\"Mohamed\",\"nom\":\"Diallo\",\"telephone\":\"773265912\"}"

  Scenario: Get request for 1 client
    Given : I wanna start a browser
    When : I do a Get request at "http://localhost:9001/clients/1"
    Then : I receive "{\"id\":1,\"prenom\":\"Mohamed\",\"nom\":\"Diallo\",\"telephone\":\"773265912\"}"

  Scenario: Get request for a client account
    Given : I wanna start a browser
    When : I do a Get request at "http://localhost:9001/clients/1/compte"
    Then : I receive "{\"id\":1,\"solde\":}"