import java.util.Date;

class Cow {
    private int id; // ID sapi
    private String name; // Nama sapi
    private Date birthDate; // Tanggal lahir sapi
    private double maintenanceCost; // Biaya perawatan

    public Cow(int id, String name, Date birthDate, double maintenanceCost) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.maintenanceCost = maintenanceCost;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Date getBirthDate() { return birthDate; }
    public double getMaintenanceCost() { return maintenanceCost; }
    public String getCategory() { return "General Cow"; }

    public void setName(String name) { this.name = name; }
    public void setMaintenanceCost(double maintenanceCost) { this.maintenanceCost = maintenanceCost; }

    @Override
    public String toString() {
        return String.format("Cow{id=%d, name='%s', birthDate=%s, maintenanceCost=%.2f}", 
                             id, name, birthDate, maintenanceCost);
    }
}
