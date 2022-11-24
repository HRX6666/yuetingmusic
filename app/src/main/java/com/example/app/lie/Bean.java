package com.example.app.lie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Bean {

    public class Sheet implements Serializable {
        private String name;
        private List<Music> list;

        public Sheet() {
            name="New List";
            list=new ArrayList<>();
        }
        public Sheet(String name){
            this.name=name;
            list=new ArrayList<>();
        }

        public  Sheet(String name, int type){
            this.name=name;
            switch(type){
                case 0:
                    list=new ArrayList<>();
                    break;
                case 1:
                    list=new LinkedList<>();
                    break;
                default:break;
            }
        }

        public String getName() {
            return name;
        }

        public List<Music> getList() {
            return list;
        }

        public void setList(java.util.List<Music> musicList){
            list=musicList;
        }

        public void add(Music music){
            list.add(music);
        }

        public void remove(Music music){
            list.remove(music);
        }

        public String getNumString(){
            if(list!=null) {
                return " " + list.size() + " 首音乐";
            }
            return " " + 0 + " 首音乐";
        }


        public boolean contains(Music song){

            return list.contains(song);
        }

        public void addFirst(Music music){
            if(list instanceof LinkedList) {
                ((LinkedList<Music>) list).addFirst(music);
            }
        }
    }

}
