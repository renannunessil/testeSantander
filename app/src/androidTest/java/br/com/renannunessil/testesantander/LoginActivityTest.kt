package br.com.renannunessil.testesantander

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import br.com.renannunessil.testesantander.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun loginUiTest() {
        onView(withId(R.id.et_login_username)).perform(clearText(), typeText("teste"))
        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)
        onView(withId(R.id.et_login_password)).perform(clearText(), typeText("teste"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.bt_login)).perform(click())
        Thread.sleep(5000)
        onView(withId(R.id.tv_home_name)).check(ViewAssertions.matches(withText("Jose da Silva Teste")))
    }
}