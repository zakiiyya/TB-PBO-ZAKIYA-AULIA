import java.util.Date;

class BeefCow extends Cow {
    private double weight;

    public BeefCow(int id, String name, Date birthDate, double maintenanceCost, double weight) {
        super(id, name, birthDate, maintenanceCost);
        this.weight = weight;
    }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public String getCategory() { return "Beef Cow"; }

    @Override
    public String toString() {
        return super.toString() + String.format(", weight=%.2f", weight);
    }
}
