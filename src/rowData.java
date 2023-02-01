import java.util.Comparator;

public class rowData implements Comparator<rowData> {
    private String objectID, diameter;
    private String depthUS, depthDS, height, invertUS, invertDS, width, pSlope, shapeLength;
    private String facilityID, legacyID, location, date, material,
            form, adminArea, operationalArea, owner;


    public rowData(String objectID,String facilityID, String legacyID, String location, String depthUS, String depthDS,
                   String height, String invertUS, String invertDS, String diameter, String width, String date,
                   String material, String form,  String pSlope, String adminArea,  String shapeLength,
                   String operationalArea, String owner) {
        this.objectID = objectID;
        this.diameter = diameter;
        this.depthUS = depthUS;
        this.depthDS = depthDS;
        this.height = height;
        this.invertUS = invertUS;
        this.invertDS = invertDS;
        this.width = width;
        this.pSlope = pSlope;
        this.shapeLength = shapeLength;
        this.facilityID = facilityID;
        this.legacyID = legacyID;
        this.location = location;

        this.date = date;
        this.material = material;
        this.form = form;
        this.adminArea = adminArea;
        this.operationalArea = operationalArea;
        this.owner = owner;
    }

    public String getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(String facilityID) {
        this.facilityID = facilityID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getLegacyID() {
        return legacyID;
    }

    public void setLegacyID(String legacyID) {
        this.legacyID = legacyID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }

    public String getOperationalArea() {
        return operationalArea;
    }

    public void setOperationalArea(String operationalArea) {
        this.operationalArea = operationalArea;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-50s%-10s%-10s%-10s%-15s%-15s%-10s%-10s%-30s%-20s%-15s%-10s%-15s%-20s%-20s%-20s%n",
                objectID, facilityID, legacyID, location, (depthUS.equals(""))? "" : depthUS, (depthDS.equals("")) ? "": depthDS,
                (height.equals(""))? "" : height, (invertUS.equals("")) ? "": invertUS, (invertDS.equals("")) ? "" : invertDS,
                (diameter.equals("")) ? "": diameter, (width.equals("")) ? "": width, (date.equals(""))? "" :date, (material.equals(""))? "":material,
                (form.equals("")? "" : form), (pSlope.equals("")) ? "": pSlope, (adminArea.equals("")? "": adminArea), (shapeLength.equals("")) ? "" : shapeLength,
                (operationalArea.equals("")) ? "": operationalArea, (owner.equals(""))? "" : owner);
    }

    public String getDepthUS() {
        return depthUS;
    }

    public void setDepthUS(String depthUS) {
        this.depthUS = depthUS;
    }

    public String getDepthDS() {
        return depthDS;
    }

    public void setDepthDS(String depthDS) {
        this.depthDS = depthDS;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getInvertUS() {
        return invertUS;
    }

    public void setInvertUS(String invertUS) {
        this.invertUS = invertUS;
    }

    public String getInvertDS() {
        return invertDS;
    }

    public void setInvertDS(String invertDS) {
        this.invertDS = invertDS;
    }

    public String getpSlope() {
        return pSlope;
    }

    public void setpSlope(String pSlope) {
        this.pSlope = pSlope;
    }

    public String getShapeLength() {
        return shapeLength;
    }

    public void setShapeLength(String shapeLength) {
        this.shapeLength = shapeLength;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }


    @Override
    public int compare(rowData s1, rowData s2) {
        return s1.getDate().compareTo(s2.getDate());
    }
}
