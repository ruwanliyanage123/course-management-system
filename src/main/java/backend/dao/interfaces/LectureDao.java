package backend.dao.interfaces;

import java.util.List;

public interface LectureDao <Lecture>{
    public abstract String[][] getAllLectures();

    public abstract void addLecture(Lecture lecture);

    public abstract Lecture getOneLecture();

    public abstract void updateLecture(Lecture lecture);

    public abstract void deleteLecture(String nic);
}
