package com.spaghettininjas.yaposs.dto;

import com.spaghettininjas.yaposs.entity.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TransactionIEnumerablePaginatableResponseObject {
    private List<Transaction> data;
    private String nextPage;
}
