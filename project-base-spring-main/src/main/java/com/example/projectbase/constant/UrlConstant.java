package com.example.projectbase.constant;

public class UrlConstant {

  public static class Auth {
    private static final String PRE_FIX = "/auth";

    public static final String LOGIN = PRE_FIX + "/login";
    public static final String LOGOUT = PRE_FIX + "/logout";

    private Auth() {
    }
  }

  public static class User {
    private static final String PRE_FIX = "/user";
    public static final String GET_USERS = PRE_FIX;
    public static final String CREATE_USER = PRE_FIX;
    public static final String CHANGE_PASSWORD = PRE_FIX;
    public static final String GET_USER = PRE_FIX + "/{userId}";
    public static final String GET_CURRENT_USER = PRE_FIX + "/current";
    public static final String DELETE_USER = PRE_FIX;
    private User() {
    }
  }

  public static class Question {
    private static final String PRE_FIX = "/question";
    public static final String CREATE_QUESTION = PRE_FIX;
    public static final String GET_QUESTIONS = PRE_FIX;
    public static final String DELETE_QUESTION = PRE_FIX;
    private Question() {}
  }

  public static class Answer {
    private static final String PRE_FIX = "/answer";
    public static final String CREATE_ANSWER = PRE_FIX;
    public static final String GET_ANSWERS = PRE_FIX;
    public static final String UPDATE_ANSWER = PRE_FIX;
    public static final String DELETE_ANSWER = PRE_FIX;
    private Answer() {}
  }
}
