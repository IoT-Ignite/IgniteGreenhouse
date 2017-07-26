package com.ardic.android.iotignite.lib.restclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by codemania on 7/24/17.
 */

/*
{
  "code": "200",
  "subCode": null,
  "status": "OK",
  "result": null,
  "message": null,
  "exception": null,
  "description": null,
  "success": "OK",
  "error": null,

  "ok": true,
  "sent": true
}
 */

public class DeviceNodeInventory {

    @SerializedName("code")
    private String code;

    @SerializedName("subCode")
    private String subCode;

    @SerializedName("status")
    private String status;

    @SerializedName("result")
    private String result;

    @SerializedName("message")
    private String message;

    @SerializedName("exception")
    private String exception;

    @SerializedName("description")
    private String description;

    @SerializedName("success")
    private String success;

    @SerializedName("error")
    private String error;

    @SerializedName("extras")
    private DeviceNodeInventoryExtras extras;

    @SerializedName("ok")
    private boolean ok;

    @SerializedName("sent")
    private boolean sent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DeviceNodeInventoryExtras getExtras() {
        return extras;
    }

    public void setExtras(DeviceNodeInventoryExtras extras) {
        this.extras = extras;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    @Override
    public String toString() {
        return "DeviceNodeInventory{" +
                "code='" + code + '\'' +
                ", subCode='" + subCode + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", description='" + description + '\'' +
                ", success='" + success + '\'' +
                ", error='" + error + '\'' +
                ", extras=" + extras +
                ", ok=" + ok +
                ", sent=" + sent +
                '}';
    }
}
