package com.mai.solar.energyControl.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum EnergyType  {

    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    private final String type;

    public static String[] toStringArray() {
        return Arrays.stream(values()).map(Enum::toString).toArray(String[]::new);
    }

    public static String[] toStringArrayFilter(String role){
        return Arrays.stream(values()).map(Enum::toString).filter(i -> !i.equals(role)).toArray(String[]::new);
    }

    public static String[] toStringArrayFilter(List<String> roles){
        return Arrays.stream(values()).map(Enum::toString).filter(i -> !roles.contains(i)).toArray(String[]::new);
    }

}
