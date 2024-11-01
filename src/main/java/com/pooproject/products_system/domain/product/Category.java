package com.pooproject.products_system.domain.product;

public enum Category {
    FRESCOS,
    PROCESSADOS,
    LATICINIOS,
    GRAOSECEREAIS,
    DOCES,
    BEBIDAS;

    public static Category convertStringToCategory(String selectedItem) {
        return switch (selectedItem) {
            case "Frescos" -> Category.FRESCOS;
            case "Processados" -> Category.PROCESSADOS;
            case "Laticínios" -> Category.LATICINIOS;
            case "Grãos & Cereais" -> Category.GRAOSECEREAIS;
            case "Doces" -> Category.DOCES;
            case "Bebidas" -> Category.BEBIDAS;
            default -> null;
        };
    }

    public static String convertCategoryToString(Category selectedItem) {
        return switch (selectedItem) {
            case Category.FRESCOS -> "Frescos";
            case Category.PROCESSADOS -> "Processados";
            case Category.LATICINIOS -> "Laticínios";
            case Category.GRAOSECEREAIS -> "Grãos & Cereais";
            case Category.DOCES -> "Doces";
            case Category.BEBIDAS -> "Bebidas";
        };
    }
}
