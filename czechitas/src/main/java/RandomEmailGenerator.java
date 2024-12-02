import java.util.Random;

public class RandomEmailGenerator {

    // function for generating random surname
    public static String randomNameGenerator() {
        String[] surnames = {"novak@seznam.cz", "stastny@seznam.cz", "novotny@seznam.cz", "hrubes@seznam.cz", "sova@seznam.cz" , "hruda@seznam.cz" , "bobes@seznam.cz" , "Luza@seznam.cz" , "Peroutka@seznam.cz" , "Chaloupka@seznam.cz" , "Sovak@seznam.cz" , "Brabec@seznam.cz" , "Hrubec@seznam.cz" , "Gudas@seznam.cz", "nov@seznam.cz", "stast@seznam.cz", "novot@seznam.cz", "hrub@seznam.cz", "sov@seznam.cz" , "hru@seznam.cz" , "bob@seznam.cz" , "Luz@seznam.cz" , "Perout@seznam.cz" , "Chaloupka@seznam.cz" , "Sov@seznam.cz" , "Brab@seznam.cz" , "Hrubec@seznam.cz" , "Gudas@seznam.cz" };
        Random random = new Random();
        int randomIndex = random.nextInt(surnames.length);  // random index
        return surnames[randomIndex];
    }
}

