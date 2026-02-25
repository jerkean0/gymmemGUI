package config;

public class Session {
    private static Session instance;
    private int id;
    private String name;
    private String email;
    private String type; 
    
    // NEW: Variable to store the selected Member ID for payments
    private String selectedMemberID; 

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Existing Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getType() { 
        return type; 
    }
    
    public void setType(String type) { 
        this.type = type; 
    }

    // NEW: Getter and Setter for passing Member ID between forms
    public String getSelectedMemberID() {
        return selectedMemberID;
    }

    public void setSelectedMemberID(String selectedMemberID) {
        this.selectedMemberID = selectedMemberID;
    }
}