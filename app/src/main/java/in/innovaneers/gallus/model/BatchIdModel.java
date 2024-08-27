package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchIdModel {
    @SerializedName("BatchID")
    private String BatchID ;

    public BatchIdModel(String batchID) {
        BatchID = batchID;
    }

    public String getBatchID() {
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }
}
