package com.example.grindandgrow.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.grindandgrow.models.User
import com.example.grindandgrow.navigation.ROUTE_LOGIN
import com.example.grindandgrow.navigation.ROUTE_REGISTER
import com.example.grindandgrow.navigation.ROUTE_HOME

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class AuthViewModel(var navController: NavHostController,var context: Context) {
    var mAuth = FirebaseAuth.getInstance()

    //register function
    fun signup(Username: String, email: String, password: String, confirmpass: String) {
        //validation
        if (email.isBlank() || password.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(context, "Email and password cannot be  blank", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmpass) {
            Toast.makeText(context, "password does not match", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userdata =
                            User(Username, email, password, mAuth.currentUser!!.uid, "user")

                        //realtime db
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + mAuth.currentUser!!.uid)
                        regRef.setValue(userdata).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "user registered successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Toast.makeText(
                                    context,
                                    "${it.exception!!.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_REGISTER)
                            }
                        }
                    } else {
                        navController.navigate(ROUTE_REGISTER)
                    }
                }
        }
    }


    //login function
    fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "successful log in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_HOME)
            } else {
                Toast.makeText(context, "error logging in", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
