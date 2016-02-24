package com.herghost.dashhab.app.Common.Results;

import java.util.ArrayList;

/**
 * Created by hergh on 21/02/2016.
 * Collection of OpenHab Results
 */
public class OHResults {


    String type;
    String name;
    String state;
    String link;
    ArrayList<members> _members = new ArrayList<>();
    public class members
    {
        String type;
        String name;
        String state;
        String link;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public ArrayList<members> get_members() {
        return _members;
    }

    public void set_members(ArrayList<members> _members) {
        this._members = _members;
    }
}
