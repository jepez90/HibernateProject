/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.hibernate_project.controllers;

import com.jp.hibernate_project.connection.EMUtil;
import com.jp.hibernate_project.connection.exceptions.NonexistentEntityException;
import com.jp.hibernate_project.connection.exceptions.PreexistingEntityException;
import com.jp.hibernate_project.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author User
 */
public class UserJpaControllerTest {

    private EntityManagerFactory em;
    private User userTest;
    private UserJpaController instance;

    public UserJpaControllerTest() {
        em = EMUtil.getFactory();
    }

    @Before
    public void setUp() {
        userTest = new User("testUser", "my User Test", User.encodePass("wes"));
        userTest.setDocument("12345");
        instance = new UserJpaController(em);
    }

    @After
    public void tearDown() {
        if (userTest == null)
            return;
        if (userTest.getId() != 0) {
            try {
                instance.destroy(userTest.getId());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(UserJpaControllerTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Test of create method, of class UserJpaController.
     */
    @org.junit.Test
    public void testCreate() throws Exception {
        System.out.println("create");
        instance.create(userTest);
        assertTrue(userTest.getId() != 0);

        //test error when nick exists
        User user2 = new User("testUser", "my User Test", User.encodePass("wes"));
        user2.setDocument("123456");

        Exception exception = assertThrows(PreexistingEntityException.class, () -> {
            instance.create(user2);
        });
        String expectedMessage = "Existe";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        //test error when doc exists
        user2.setDocument("12345");
        user2.setNick("testUser2");

        exception = assertThrows(PreexistingEntityException.class, () -> {
            instance.create(user2);
        });
        expectedMessage = "Existe";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

    /**
     * Test of edit method, of class UserJpaController.
     */
    @org.junit.Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        User user = null;
        UserJpaController instance = null;
        instance.edit(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class UserJpaController.
     */
    @org.junit.Test
    public void testDestroy() throws Exception {

        System.out.println("destroy");
        instance.create(userTest);
        int id = userTest.getId();
        instance.destroy(id);
        User us= instance.findUser(id);
        assertEquals(us, null);
    }

    /**
     * Test of findUser method, of class UserJpaController.
     */
    @org.junit.Test
    public void testFindUser() {
        System.out.println("findUser");
        Integer id = null;
        UserJpaController instance = null;
        User expResult = null;
        User result = instance.findUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByNick method, of class UserJpaController.
     */
    @org.junit.Test
    public void testFindUserByNick() {
        System.out.println("findUserByNick");
        String nick = "testUser";
        UserJpaController instance = new UserJpaController(em);
        User expResult = null;
        User result = instance.findUserByNick(nick);
        assertEquals(nick, result.getNick());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserByDoc method, of class UserJpaController.
     */
    @org.junit.Test
    public void testFindUserByDoc() {
        System.out.println("findUserByDoc");
        String doc = "";
        UserJpaController instance = null;
        User expResult = null;
        User result = instance.findUserByDoc(doc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
