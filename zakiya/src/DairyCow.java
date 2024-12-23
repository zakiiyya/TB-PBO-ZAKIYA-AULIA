import java.util.Date;

class DairyCow extends Cow {
    private double milkProduction;

    public DairyCow(int id, String name, Date birthDate, double maintenanceCost, double milkProduction) {
        super(id, name, birthDate, maintenanceCost);
        this.milkProduction = milkProduction;
    }

    public double getMilkProduction() { return milkProduction; }
    public void setMilkProduction(double milkProduction) { this.milkProduction = milkProduction; }

    @Override
    public String getCategory() { return "Dairy Cow"; }

    @Override
    public String toString() {
        return super.toString() + String.format(", milkProduction=%.2f", milkProduction);
    }
}
