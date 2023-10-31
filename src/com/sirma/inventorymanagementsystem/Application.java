package com.sirma.inventorymanagementsystem;

import com.sirma.inventorymanagementsystem.models.cli.HomeMenuCli;

import java.util.Scanner;

public class Application {

    private static Scanner scanner = new Scanner(System.in);
    private static HomeMenuCli homeMenuCli = new HomeMenuCli(scanner);

    public static void main(String[] args) {

        homeMenuCli.showMenu();

    }
}
