import Link from "next/link";
import {ExternalLinkIcon} from "lucide-react";

export default function ShortcutButton({
                                         label,
                                         url,
                                       }: {
  label: string;
  url: string;
}) {

  return (
    <div>
      <Link
        href={url}
        className="inline-block py-3 text-black dark:text-white bg-white dark:bg-gray-800 rounded-lg shadow-md text-center text-lg transition-colors duration-200
        hover:bg-black hover:text-white dark:hover:bg-white dark:hover:text-black"
        target="_blank"
        rel="noopener noreferrer"
      >
        {`Let's Go `}{label}
        <ExternalLinkIcon className="inline-block w-5 h-5 ml-2"/>
      </Link>
    </div>

  );
}
