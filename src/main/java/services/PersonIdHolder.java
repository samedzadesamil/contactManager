package services;

public class PersonIdHolder {

    private static final PersonIdHolder INSTANCE = new PersonIdHolder();

    private long personId;

    private PersonIdHolder() {
        PersonContactService personContactService = new PersonContactFileService();
        personId = personContactService.getLatestPersonId();
    }

    public static PersonIdHolder getInstance() {
        return INSTANCE;
    }

    public long nextId() {
        return ++personId;
    }

}
