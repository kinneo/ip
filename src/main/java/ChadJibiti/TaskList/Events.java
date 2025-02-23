package ChadJibiti.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected String from;
    protected String to;

    public Events(String description, String from, String to){
        super(description);
        if (isValidDateTime(from.trim())){
            this.from = formatDateTime(from.trim());
        }else{
            this.from = from;
        }
        if (isValidDateTime(to)){
            this.to = formatDateTime(to).trim();
        }else{
            this.to = to;
        }
    }

    private boolean isValidDateTime(String line){
        try {
            DateTimeFormatter output = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime.parse(line, output);
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
        return dateTime.format(dateFormatter) + " " + dateTime.format(timeFormatter) + " ";
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
