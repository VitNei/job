package com.bignerdranch.android.criminalintent;

public class CrimeDbSchema {
    public static final class CrimeTable{
        public static final String NAME_JOB = "job";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String NOTE = "note";
            public static final String DATE = "date";
            public static final String DATE_CHANGE = "dateChange";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
            public static final String PRIORITET = "prioritet";
            public static final String PROGRESS = "progress";
        }
    }

    public static final class TableCompleted{
        public static final String NAME_TABLE_COMPLETED = "completedJob";

        public static final class Cols{
            public static final String UUID_TABLE_COMPLETED = "uuidCompleted";
            public static final String TITLE_TABLE_COMPLETED = "titleCompleted";
            public static final String NOTE_TABLE_COMPLETED = "noteCompleted";
            public static final String DATE_CREATE_TABLE_COMPLETED = "dateCreateTableCompleted";
            public static final String DATE_FINISH_TABLE_COMPLETED = "dateFinishTableCompleted";
        }
    }
}
