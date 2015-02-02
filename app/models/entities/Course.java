package models.entities;

import play.data.validation.Constraints.Required;
import play.libs.Json;

public class Course {


    public static int id = 0;

    @Required()
    public String title;

    @Required()
    public String sector;

    @Required()
    public String registrationLimit;

    @Required()
    public Duration duration;

    @Required()
    public String location;

    @Required()
    public String description;

    @Required()
    public String general_terms;

    @Required()
    public String requirements;

    @Required()
    public double price;

    @Required()
    public ContactProfile contact;

    public Course() {
    }

    public Course(String title, String sector, String registrationLimit, Duration duration, String location,
                  String description, String general_terms, String requirements, double price, ContactProfile contact) {
        this.id = id + 1;
        this.title = title;
        this.sector = sector;
        this.registrationLimit = registrationLimit;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.general_terms = general_terms;
        this.requirements = requirements;
        this.price = price;
        this.contact = contact;
    }

    public String showCourseOfferInfo() {
        return Json.toJson(this).toString();
    }

    public static class Duration {
        @Required()
        public String length;

        @Required()
        public String schedule;

        @Required()
        public String start_date;

        @Required()
        public String end_date;

        public Duration(String length, String schedule, String start_date, String end_date) {
            this.length = length;
            this.schedule = schedule;
            this.start_date = start_date;
            this.end_date = end_date;
        }

        public String getLength() {
            return length;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getSchedule() {
            return schedule;
        }

        public String getEnd_date() {
            return end_date;
        }

        public String toString() {
            return "{\"length\":\"" + this.length + "\",\"schedule\":\"" + this.schedule + "\",\"start_date\":\"" + this.start_date + "\",\"end_date\":\"" + this.end_date + "\"}";
        }
    }

    public static class ContactProfile {
        @Required()
        public String name;

        @Required()
        public String email;

        @Required()
        public int phone_number;

        public ContactProfile(String name, String email, int phone_number) {
            this.name = name;
            this.email = email;
            this.phone_number = phone_number;
        }

        public String toString() {
            return "{\"name\":\"" + this.name + "\",\"email\":\"" + this.email + "\",\"phone_number\":\"" + this.phone_number + "\"}";
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getPhone_number() {
            return phone_number;
        }
    }

    public static int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSector() {
        return sector;
    }

    public String getRegistrationLimit() {
        return registrationLimit;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getGeneral_terms() {
        return general_terms;
    }

    public String getRequirements() {
        return requirements;
    }

    public double getPrice() {
        return price;
    }

    public ContactProfile getContact() {
        return contact;
    }
}
