package com.example.library.controller.request;

import lombok.Data;

@Data
public class BookPageRequest extends BaseRequest{
    private String name;
    private String bookNo;
}
