package com.skola;

public enum BookStatus
{//ja sam ovo na fazon enuma mada sam krenuo sa stringom na pocetku
    DOSTUPNA("Dostupna"),
    IZDATA("Izdata");

    private final String status;

    BookStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }

    public static BookStatus fromString(String status) throws IllegalArgumentException
    {
        for (BookStatus bs : BookStatus.values())
        {
            if (bs.status.equalsIgnoreCase(status))
            {
                return bs;
            }
        }
        throw new IllegalArgumentException("Nepostojeci status: " + status);
    }
}
