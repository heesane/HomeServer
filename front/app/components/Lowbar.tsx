export default function Lowbar() {
    return (
      <footer className="fixed bottom-0 w-full bg-white dark:bg-gray-800 dark:border-gray-700 border-t border-gray-200 dark:border-gray-700 p-4 md:px-8 flex items-center justify-between">
        <div className="max-w-screen-lg w-full mx-auto text-sm text-gray-500 dark:text-gray-400">© 2024 Heesang Portfolio. All rights reserved.</div>
        <nav className="hidden md:block">
          <ul className="flex space-x-4">
            <li>
              <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
                Privacy Policy
              </a>
            </li>
            <li>
              <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
                Terms of Service
              </a>
            </li>
          </ul>
        </nav>
      </footer>
    );
  }
  