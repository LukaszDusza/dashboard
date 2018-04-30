package com.dfsp.dashboard.config;

import com.dfsp.dashboard.app.FilesReader;

public class Main {

    public static void main(String[] args) {

        System.out.println(new FilesReader().readLangfile("dictionary","pol"));

        }

    }

