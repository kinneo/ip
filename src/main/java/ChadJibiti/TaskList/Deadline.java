package ChadJibiti.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by){
        super(description);
        if (isValidDateTime(by)){
            this.by = formatDateTime(by);
        }else{
            this.by = by;
        }
    }

    private boolean isValidDateTime(String by){
        try {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(by, output);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private String formatDateTime(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormatter);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        return dateTime.format(dateFormatter) + " " + dateTime.format(timeFormatter);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
