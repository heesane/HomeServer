// Pagination.tsx
import React from 'react';

export function Pagination() {
  return (
    <nav aria-label="Pagination">
      <ul className="flex space-x-2">
        <li>
          <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
            Previous
          </a>
        </li>
        <li>
          <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
            1
          </a>
        </li>
        <li>
          <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
            2
          </a>
        </li>
        <li>
          <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
            3
          </a>
        </li>
        <li>
          <a className="text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-200" href="#">
            Next
          </a>
        </li>
      </ul>
    </nav>
  );
}
