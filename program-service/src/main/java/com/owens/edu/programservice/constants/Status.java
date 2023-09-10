package com.owens.edu.programservice.constants;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    APPROVED("Approved"),
    SUBMITTED("Submitted"),
    PENDING_APPROVAL("Pending Approval"),
    PAID("Paid"),
    PENDING_PAYMENT("Pending Payment"),
    UNPAID("Unpaid"),
    AWAITING_PAYMENT("Awaiting Payment"),
    REGISTERED("Registered"),
    DEREGISTERED("Deregistered"),
    REG_COMPLETE_PENDING("Reg Complete - Pending"),
    INCOMPLETE_REG("Incomplete Reg - Payment Required"),
    NOT_PAID("Not Paid"),
    PAYMENT_DUE("Payment Due"),
    PAYMENT_OUTSTANDING("Payment Outstanding"),
    PAYMENT_PROCESSING("Payment Processing"),
    PARTIAL_PAYMENT("Partial Payment"),
    PAYMENT_FAILED("Payment Failed"),
    PAYMENT_CANCELLED("Payment Cancelled"),
    CANCELLED("Cancelled"),
    UPCOMING("Upcoming"),
    SCHEDULED("Scheduled"),
    COMPLETED("Completed");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    Status() {
        this.displayName = name();
    }

}
