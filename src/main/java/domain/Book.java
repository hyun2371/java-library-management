package domain;


import lombok.Getter;

import java.time.Duration;
import java.time.Instant;

@Getter
public class Book {
    private final Integer id;
    private final String title;
    private final String author;
    private final Integer page;
    private Status status;
    private Instant returnTime;

    public Book(Integer id, String title, String author, int page, Status status, Instant returnTime) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
        this.status = status;
        this.returnTime = returnTime;
    }

    public Book(Integer id, String title, String author, Integer page) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
        this.status = Status.AVAILABLE;
        this.returnTime = null;
    }


    public void printBookInfo() {
        System.out.println("\n" +"도서번호 : "+ id +"\n"
                +"제목 : " + title + "\n"
                +"작가 이름 : " + author + "\n"
                +"페이지 수 : " + page + "\n"
                +"상태 : " + status.getLabel()+ "\n\n"
                +"-------------------------------------"
        );
    }

    //책 상태 관련 함수
    public void borrow(){
        status = Status.BORROWED;
    }

    public void doReturn() {
        returnTime = Instant.now();
        status = Status.CLEANING;
    }

    public boolean isCleaning(){
        if (Duration.between(returnTime, Instant.now()).toMinutes()<5) {
            status = Status.AVAILABLE;
            return true;
        }
        return false;
    }

    public void report(){
        status = Status.LOST;
    }
}