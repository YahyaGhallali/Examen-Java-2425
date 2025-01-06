package com.mri.examenjava2425;

public class MainShell {
    public static void main(String[] args) {
        SupplementDAO supplementDAO = new SupplementDAO();
        try {
            System.out.println(supplementDAO.listeSupplements());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
