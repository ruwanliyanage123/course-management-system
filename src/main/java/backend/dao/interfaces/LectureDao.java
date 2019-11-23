package backend.dao.interfaces;

public interface LectureDao <Lecture>{
    public abstract String[][] getAllLectures();

    public abstract void addLecture(Lecture lecture);

    public abstract Lecture getOneLecture(String nic1);

    public abstract void updateLecture(Lecture lecture);

    public abstract void deleteLecture(String nic);
}
