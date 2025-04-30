import { initializeApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";  

const firebaseConfig = {
  apiKey: "AIzaSyDFabmL5UH6jGW8S_hXYyFlgP7xFeMlaMg",
  authDomain: "prepbuddydb.firebaseapp.com",
  projectId: "prepbuddydb",
  storageBucket: "prepbuddydb.firebasestorage.app",
  messagingSenderId: "973086602615",
  appId: "1:973086602615:web:b258d087400a3502469d72",
  measurementId: "G-0J5N3LQS6P"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firestore
const db = getFirestore(app);

export { db };
