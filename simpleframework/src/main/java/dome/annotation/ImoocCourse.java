package dome.annotation;

@CourseInfoAnnotation(courseName = "测试", courseTag = "面试", courseProfile = "test")
public class ImoocCourse {

    @PersonInfoAnnotation(name = "李小龙他哥", language = {"java", "python"})
    private String author;

    @CourseInfoAnnotation(courseName = "测试", courseTag = "面试", courseProfile = "test")
    public void getCourseInfo() {

    }

}
