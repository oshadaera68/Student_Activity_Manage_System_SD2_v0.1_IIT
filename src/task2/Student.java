package task2;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class Student {
    private String id;
    private String name;
    private Module[] modules;

    public Student() {
    }

    public Student(String id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.modules = new Module[3];
        for (int i = 0; i < 3; i++) {
            this.modules[i] = new Module(marks[i]);
        }
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Module[] getModules() {
        return modules;
    }
}
