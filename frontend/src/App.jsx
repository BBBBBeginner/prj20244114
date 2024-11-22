import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { BoardAdd } from "./page/board/BoardAdd.jsx";
import { BoardList } from "./page/board/BoardList.jsx";
import { RootLayout } from "./page/root/RootLayout.jsx";
import { BoardView } from "./page/board/BoardView.jsx";
import { BoardEdit } from "./page/board/BoardEdit.jsx";
import { MemberSignup } from "./page/member/MemberSignup.jsx";
import { MemberList } from "./page/member/MemberList.jsx";
import React from "react";
import { MemberInfo } from "./page/member/MemberInfo.jsx";
import { MemberEdit } from "./page/member/MemberEdit.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      {
        index: true,
        element: <BoardList />,
      },
      {
        path: "add",
        element: <BoardAdd />,
      },
      {
        path: "view/:id",
        element: <BoardView />,
      },
      {
        path: "edit/:id",
        element: <BoardEdit />,
      },
      { path: "member/signup", element: <MemberSignup /> },
      { path: "member/list", element: <MemberList /> },
      {
        path: "member/:id",
        element: <MemberInfo />,
      },
      { path: "member/edit/:id", element: <MemberEdit /> },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
